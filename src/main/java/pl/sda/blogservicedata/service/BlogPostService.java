package pl.sda.blogservicedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.sda.blogservicedata.exception.BlogPostNotFoundException;
import pl.sda.blogservicedata.exception.TopicNotFoundException;
import pl.sda.blogservicedata.exception.UserNotFoundException;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.User;
import pl.sda.blogservicedata.model.mapping.BlogPostMapper;
import pl.sda.blogservicedata.model.request.BlogPostDto;
import pl.sda.blogservicedata.repository.BlogPostRepository;
import pl.sda.blogservicedata.repository.FilteringBlogPostRepository;
import pl.sda.blogservicedata.repository.TopicRepository;
import pl.sda.blogservicedata.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final FilteringBlogPostRepository filteringBlogPostRepository;
    private final BlogPostMapper blogPostMapper;
    private UserRepository userRepository;
    private TopicRepository topicRepository;

    @Autowired
    public BlogPostService(
            BlogPostRepository blogPostRepository,
            FilteringBlogPostRepository filteringBlogPostRepository,
            BlogPostMapper blogPostMapper,
            UserRepository userRepository,
            TopicRepository topicRepository
    ) {
        this.blogPostRepository = blogPostRepository;
        this.filteringBlogPostRepository = filteringBlogPostRepository;
        this.blogPostMapper = blogPostMapper;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public BlogPost save(final BlogPostDto blogPostDto) {
        BlogPost blogPost = blogPostMapper.map(blogPostDto);
        blogPost.setCreated(LocalDateTime.now());
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public BlogPost findById(final long id) {
        return blogPostRepository.findById(id).orElseThrow(
                () -> new BlogPostNotFoundException("Could not find blog post with id: " + id));
    }

    public List<BlogPost> findByFilter(final @Nullable List<String> topics, final @Nullable Long authorId, final @Nullable String titlePhrase, @Nullable Pageable pageable) {
        User author = null;
        List<Topic> topicsFromDb = null;
        if (authorId != null) {
            author = userRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException("User not found: " + authorId));
        }
        if (topics != null && !topics.isEmpty()) {
            topicsFromDb = topics.stream().map(topic -> topicRepository.findByName(topic)
                    .orElseThrow(() -> new TopicNotFoundException("Topic not found: " + topic)))
                    .collect(Collectors.toList());
        }
        return filteringBlogPostRepository.filterBlogPosts(topicsFromDb, author, titlePhrase, pageable);
    }

    public void removeById(final long id) {
        Optional<BlogPost> blogPostToRemove = blogPostRepository.findById(id);
        blogPostToRemove
                .ifPresentOrElse(
                        blogPost -> blogPostRepository.delete(blogPost),
                        () -> {
                            throw new BlogPostNotFoundException("Could not find blog post while attempting to delete");
                        });
    }

}

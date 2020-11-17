package pl.sda.blogservicedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.sda.blogservicedata.exception.BlogPostNotFoundException;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.mapping.BlogPostMapper;
import pl.sda.blogservicedata.model.request.BlogPostDto;
import pl.sda.blogservicedata.repository.BlogPostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public BlogPostService(final BlogPostRepository blogPostRepository, final BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
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

    public List<BlogPost> findByFilter(final Topic topic, final String author, String titlePhrase) {
        if (topic != null && author != null && titlePhrase != null) {
            return blogPostRepository.findByFilters(topic, author, titlePhrase);
        }
        if (topic != null && author != null) {
            return blogPostRepository.findAllByTopicAndAuthor(topic, author);
        }
        if (topic != null && titlePhrase != null) {
            return blogPostRepository.findAllByTopicAndTitleContaining(topic, titlePhrase);
        }
        if (titlePhrase != null && author != null) {
            return blogPostRepository.findAllByAuthorAndTitleContaining(author, titlePhrase);
        }
        if (titlePhrase != null) {
            return blogPostRepository.findAllByTitleContaining(titlePhrase);
        }
        if (author != null) {
            return blogPostRepository.findAllByAuthor(author);
        }
        if (topic != null) {
            return blogPostRepository.findAllByTopic(topic);
        }
        return blogPostRepository.findAll();
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

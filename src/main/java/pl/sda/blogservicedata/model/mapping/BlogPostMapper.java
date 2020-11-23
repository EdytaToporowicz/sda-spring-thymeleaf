package pl.sda.blogservicedata.model.mapping;

import org.springframework.stereotype.Component;
import pl.sda.blogservicedata.exception.TopicNotFoundException;
import pl.sda.blogservicedata.exception.UserNotFoundException;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.request.BlogPostDto;
import pl.sda.blogservicedata.repository.TopicRepository;
import pl.sda.blogservicedata.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogPostMapper {

    private UserRepository userRepository;
    private TopicRepository topicRepository;

    public BlogPostMapper(UserRepository userRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public BlogPost map(final BlogPostDto blogPostDto) {
        final BlogPost blogPost = new BlogPost();
        blogPost.setAuthor(userRepository.findById(blogPostDto.getAuthorId())
                .orElseThrow(() -> new UserNotFoundException("Author not found: " + blogPostDto.getAuthorId())));
        blogPost.setTitle(blogPostDto.getTitle());
        blogPost.setContent(blogPostDto.getContent());
        List<Topic> topicsList = blogPostDto.getTopics().stream()
                .map(topic -> topicRepository.findById(topic)
                        .orElseThrow(() -> new TopicNotFoundException("Topic not found in catalogue: " + topic)))
                .collect(Collectors.toList());
        blogPost.setTopics(topicsList);
        return blogPost;
    }
}

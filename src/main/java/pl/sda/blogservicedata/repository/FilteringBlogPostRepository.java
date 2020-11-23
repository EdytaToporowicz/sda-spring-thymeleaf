package pl.sda.blogservicedata.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.User;

import java.util.List;

@Repository
public class FilteringBlogPostRepository {

    private BlogPostRepository blogPostRepository;
    private SimpleJpaRepository<BlogPost, Long> blogPostLongSimpleJpaRepository;
    private UserRepository userRepository;

    public FilteringBlogPostRepository(BlogPostRepository blogPostRepository, SimpleJpaRepository<BlogPost, Long> blogPostLongSimpleJpaRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostLongSimpleJpaRepository = blogPostLongSimpleJpaRepository;
        this.userRepository = userRepository;
    }

    public List<BlogPost> filterBlogPosts(List<Topic> topics, User author, String titlePhrase, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("topics", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id", "content", "created", "modified");
        Example<BlogPost> example = Example.of(
                new BlogPost(0l, author, titlePhrase, null, topics, null, null, null),
                matcher);
        return pageable == null ?
                blogPostRepository.findAll(example) : blogPostRepository.findAll(example, pageable).getContent();
//        Specification<BlogPost> spec = Specification.where(BlogPostSpecification.hasTopic(topic))
//                .and(BlogPostSpecification.hasAuthor(author))
//                .and(BlogPostSpecification.containsTitlePhrase(titlePhrase));
//        return blogPostLongSimpleJpaRepository.findAll(spec);
    }
}

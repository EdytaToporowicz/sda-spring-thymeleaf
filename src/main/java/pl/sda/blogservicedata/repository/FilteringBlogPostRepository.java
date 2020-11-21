package pl.sda.blogservicedata.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;

import java.util.List;

@Repository
public class FilteringBlogPostRepository {

    private BlogPostRepository blogPostRepository;
    private SimpleJpaRepository<BlogPost, Long> blogPostLongSimpleJpaRepository;

    public FilteringBlogPostRepository(BlogPostRepository blogPostRepository, SimpleJpaRepository<BlogPost, Long> blogPostLongSimpleJpaRepository) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostLongSimpleJpaRepository = blogPostLongSimpleJpaRepository;
    }

    public List<BlogPost> filterBlogPosts(Topic topic, String author, String titlePhrase, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("topic", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id", "content", "created", "modified");
        Example<BlogPost> example = Example.of(
                new BlogPost(0l, author, titlePhrase, null, topic, null, null),
                matcher);

        return blogPostRepository.findAll(example, pageable).getContent();
//        Specification<BlogPost> spec = Specification.where(BlogPostSpecification.hasTopic(topic))
//                .and(BlogPostSpecification.hasAuthor(author))
//                .and(BlogPostSpecification.containsTitlePhrase(titlePhrase));
//        return blogPostLongSimpleJpaRepository.findAll(spec);
    }
}

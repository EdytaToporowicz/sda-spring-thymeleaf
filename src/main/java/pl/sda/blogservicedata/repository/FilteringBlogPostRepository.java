package pl.sda.blogservicedata.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.User;

import java.util.List;

@Repository
public class FilteringBlogPostRepository {

    private SimpleJpaRepository<BlogPost, Long> blogPostLongSimpleJpaRepository;

    public FilteringBlogPostRepository(SimpleJpaRepository<BlogPost, Long> blogPostLongSimpleJpaRepository) {
        this.blogPostLongSimpleJpaRepository = blogPostLongSimpleJpaRepository;
    }

    public List<BlogPost> filterBlogPosts(Topic topic, User author, String titlePhrase, Pageable pageable) {
        Specification<BlogPost> spec = Specification.where(BlogPostSpecification.hasTopic(topic))
                .and(BlogPostSpecification.hasAuthor(author))
                .and(BlogPostSpecification.containsTitlePhrase(titlePhrase));
        return pageable == null ?
                blogPostLongSimpleJpaRepository.findAll(spec) :
                blogPostLongSimpleJpaRepository.findAll(spec, pageable).getContent();
    }
}

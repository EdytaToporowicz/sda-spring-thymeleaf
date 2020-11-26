package pl.sda.blogservicedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    BlogPost save(final BlogPost blogPost);

    List<BlogPost> findAll();

    void delete(BlogPost blogPost);

    Optional<BlogPost> findById(Long id);

}

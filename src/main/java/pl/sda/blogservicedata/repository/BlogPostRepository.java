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
//
//    List<BlogPost> findAllByTopic(List<Topic> topics);
//
//    List<BlogPost> findAllByAuthor(String author);
//
//    List<BlogPost> findAllByTitleContaining(String titlePhrase);
//
//    List<BlogPost> findAllByTopicAndAuthor(List<Topic> topics, String author);
//
//    List<BlogPost> findAllByTopicAndTitleContaining(List<Topic> topics, String titlePhrase);
//
//    List<BlogPost> findAllByAuthorAndTitleContaining(String author, String titlePhrase);
//
//    //    List<BlogPost> findAllByTopicAndAuthorAndTitleContaining(Topic topic, String author, String titlePhrase);
//    @Query(value = "SELECT b FROM blogPosts b WHERE b.topic = :topic AND b.author = :author and b.title LIKE %:titlePhrase%")
//    List<BlogPost> findByFilters(@Param("topic") Topic topic, @Param("author") String author, @Param("titlePhrase") String titlePhrase);

}

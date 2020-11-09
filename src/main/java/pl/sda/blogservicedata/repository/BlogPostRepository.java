package pl.sda.blogservicedata.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BlogPostRepository {

    private EntityManager entityManager;

    public BlogPostRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public BlogPost save(final BlogPost blogPost) {
        blogPost.setCreated(LocalDateTime.now());
        entityManager.persist(blogPost);
        return blogPost;
    }

    public List<BlogPost> findAll() {
        return entityManager.createQuery("SELECT b FROM blogPosts b", BlogPost.class).getResultList();
    }

    @Transactional
    public void remove(long blogPostId) {
        entityManager.createQuery("DELETE FROM blogPosts b WHERE b.id = :id")
                .setParameter("id", blogPostId)
                .executeUpdate();
    }

    public BlogPost findById(final long blogPostId) {
        return entityManager.createQuery("SELECT b FROM blogPosts b WHERE b.id = :id", BlogPost.class)
                .setParameter("id", blogPostId)
                .getSingleResult();
    }

    public List<BlogPost> findByTopic(Topic topic) {
        return entityManager.createQuery("SELECT b FROM blogPosts b WHERE b.topic = :topic", BlogPost.class)
                .setParameter("topic", topic)
                .getResultList();
    }
}

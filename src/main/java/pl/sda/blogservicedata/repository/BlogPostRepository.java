package pl.sda.blogservicedata.repository;

import org.springframework.stereotype.Repository;
import pl.sda.blogservicedata.model.BlogPost;

import java.util.List;

@Repository
public class BlogPostRepository {

    public BlogPost save(final BlogPost blogPost) {
        return null;
    }


    public List<BlogPost> findAll() {
        return null;
    }


    public void remove(BlogPost blogPost) {
    }

    public BlogPost findById(final long blogPostId) {
        return null;
    }

    public List<BlogPost> findByTopic(String topic) {
        return null;
    }
}

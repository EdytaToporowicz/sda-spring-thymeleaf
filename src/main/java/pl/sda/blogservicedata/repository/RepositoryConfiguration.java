package pl.sda.blogservicedata.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import pl.sda.blogservicedata.model.BlogPost;

import javax.persistence.EntityManager;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public SimpleJpaRepository<BlogPost, Long> blogPostSimpleJpaRepository(@Autowired EntityManager entityManager) {
        return new SimpleJpaRepository<BlogPost, Long>(BlogPost.class, entityManager);
    }
}

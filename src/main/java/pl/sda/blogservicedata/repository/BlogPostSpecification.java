package pl.sda.blogservicedata.repository;


import org.springframework.data.jpa.domain.Specification;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;

import java.time.LocalDateTime;

public class BlogPostSpecification {

    public static Specification<BlogPost> hasTopic(Topic topic) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) ->
                topic != null ? criteriaBuilder.equal(root.get("topic"), topic): null;
    }

    public static Specification<BlogPost> hasAuthor(String author) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) ->
                author != null ? criteriaBuilder.equal(root.get("author"), author) : null;
    }

    public static Specification<BlogPost> containsTitlePhrase(String titlePhrase) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) ->
                titlePhrase != null ? criteriaBuilder.like(root.get("title"), "%" + titlePhrase + "%") : null;
    }

    public static Specification<BlogPost> wasCreatedBetween(LocalDateTime from, LocalDateTime to) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) ->
                from != null && to != null ?
                        criteriaBuilder.between(root.get("created"), from, to) : null;
    }
}

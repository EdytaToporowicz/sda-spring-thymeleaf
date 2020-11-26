package pl.sda.blogservicedata.repository;


import org.springframework.data.jpa.domain.Specification;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.User;

public class BlogPostSpecification {

    public static Specification<BlogPost> hasTopic(Topic topic) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) -> {
            if (topic != null) {
                return criteriaBuilder.isMember(topic, root.get("topics"));
            }
            return null;
        };
    }

    public static Specification<BlogPost> hasAuthor(User author) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) ->
                author != null ? criteriaBuilder.equal(root.get("author"), author) : null;
    }

    public static Specification<BlogPost> containsTitlePhrase(String titlePhrase) {
        return (Specification<BlogPost>) (root, criteriaQuery, criteriaBuilder) ->
                titlePhrase != null ? criteriaBuilder.like(root.get("title"), "%" + titlePhrase + "%") : null;
    }

}

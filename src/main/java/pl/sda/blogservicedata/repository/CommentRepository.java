package pl.sda.blogservicedata.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.blogservicedata.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}

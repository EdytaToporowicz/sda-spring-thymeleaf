package pl.sda.blogservicedata.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.blogservicedata.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

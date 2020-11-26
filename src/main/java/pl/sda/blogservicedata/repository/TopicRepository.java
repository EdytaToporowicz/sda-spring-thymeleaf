package pl.sda.blogservicedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.blogservicedata.model.Topic;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, String> {

    Optional<Topic> findByName(String name);
}

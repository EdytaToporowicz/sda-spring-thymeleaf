package pl.sda.blogservicedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.blogservicedata.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, String> {

}

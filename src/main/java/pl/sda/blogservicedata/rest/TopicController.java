package pl.sda.blogservicedata.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.request.TopicDto;
import pl.sda.blogservicedata.service.TopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicDto topicDto) {
        return ResponseEntity.ok(topicService.createTopic(topicDto));
    }
}

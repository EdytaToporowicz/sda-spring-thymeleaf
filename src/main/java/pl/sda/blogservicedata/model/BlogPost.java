package pl.sda.blogservicedata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BlogPost {

    private long id;
    private String author;
    private String title;
    private String content;
    private Topic topic;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BlogPost(long id, String author, String title, String content, Topic topic, LocalDateTime created) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.created = created;
    }
}

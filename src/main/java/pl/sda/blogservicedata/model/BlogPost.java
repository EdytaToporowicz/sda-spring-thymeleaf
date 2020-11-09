package pl.sda.blogservicedata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "blogPosts")
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String author;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Topic topic;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BlogPost(String author, String title, String content, Topic topic, LocalDateTime created) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.created = created;
    }
}

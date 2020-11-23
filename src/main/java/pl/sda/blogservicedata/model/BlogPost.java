package pl.sda.blogservicedata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "blogPosts")
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private String title;
    private String content;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Topic> topics;
    @OneToMany(mappedBy = "blogPost")
    private List<Comment> comments;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BlogPost(User author, String title, String content, List<Topic> topics, LocalDateTime created) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.topics = topics;
        this.created = created;
    }
}

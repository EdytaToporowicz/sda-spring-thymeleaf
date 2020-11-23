package pl.sda.blogservicedata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private String content;
    private LocalDateTime created;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "blog_post_id")
    private BlogPost blogPost;
}

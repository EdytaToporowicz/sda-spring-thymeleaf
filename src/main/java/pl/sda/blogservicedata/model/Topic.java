package pl.sda.blogservicedata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "topics")
    private List<BlogPost> blogPosts;

}

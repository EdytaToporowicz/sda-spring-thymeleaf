package pl.sda.blogservicedata.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostForm {

    private String title;
    private String content;
    private String authorEmail;
    private List<String> selectedTopics;

}

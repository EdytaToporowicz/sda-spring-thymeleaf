package pl.sda.blogservicedata.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostForm {

    @Size(min = 5, message = "Tytuł blog posta musi mieć co najmniej 5 znaków")
    private String title;
    private String content;
    private String authorEmail;
    @Size(min = 1)
    private List<String> selectedTopics;

}

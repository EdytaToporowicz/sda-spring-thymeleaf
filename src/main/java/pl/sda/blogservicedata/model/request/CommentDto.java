package pl.sda.blogservicedata.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.blogservicedata.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long authorId;
    private String content;

}

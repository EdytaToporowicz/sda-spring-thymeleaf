package pl.sda.blogservicedata.model.mapping;

import org.springframework.stereotype.Component;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.request.BlogPostDto;

@Component
public class BlogPostMapper {

    public BlogPost map(final BlogPostDto blogPostDto) {
        final BlogPost blogPost = new BlogPost();
        blogPost.setAuthor(blogPostDto.getAuthor());
        blogPost.setTitle(blogPostDto.getTitle());
        blogPost.setContent(blogPostDto.getContent());
        blogPost.setTopic(blogPostDto.getTopic());
        return blogPost;
    }
}

package pl.sda.blogservicedata.exception;

public class BlogPostNotFoundException extends ResourceNotFoundException {

    public BlogPostNotFoundException(String message) {
        super(message);
    }
}

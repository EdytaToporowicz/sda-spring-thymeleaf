package pl.sda.blogservicedata.exception;

public class BlogPostNotFoundException extends RuntimeException {

    public BlogPostNotFoundException(String message) {
        super(message);
    }
}

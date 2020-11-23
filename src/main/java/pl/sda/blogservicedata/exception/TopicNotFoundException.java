package pl.sda.blogservicedata.exception;

public class TopicNotFoundException extends ResourceNotFoundException {

    public TopicNotFoundException(String message) {
        super(message);
    }
}

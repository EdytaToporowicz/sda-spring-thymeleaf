package pl.sda.blogservicedata.exception;

public class CommentNotFondException extends ResourceNotFoundException {

    public CommentNotFondException(String message) {
        super(message);
    }
}

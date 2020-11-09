package pl.sda.blogservicedata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogPostExceptionHandler {

    @ExceptionHandler(BlogPostNotFoundException.class)
    public ResponseEntity<BlogPostError> handleNotFound(BlogPostNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new BlogPostError("Not found error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BlogPostError> handleValidationError(MethodArgumentNotValidException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new BlogPostError("Validation error", ex.getMessage()));
    }
}

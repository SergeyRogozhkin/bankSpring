package base.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomDuplicateException extends RuntimeException {
    public CustomDuplicateException(String message) {
        super(message);
    }
}
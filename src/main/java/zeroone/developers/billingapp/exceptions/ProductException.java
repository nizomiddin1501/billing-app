package zeroone.developers.billingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when a user is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductException extends RuntimeException{

    public ProductException(String message) {
        super(message);
    }



}

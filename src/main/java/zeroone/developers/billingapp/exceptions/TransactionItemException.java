package zeroone.developers.billingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when a user is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionItemException extends RuntimeException{

    public TransactionItemException(String message) {
        super(message);
    }



}

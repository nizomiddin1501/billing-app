package zeroone.developers.billingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when a user is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserPaymentTransactionException extends RuntimeException{

    public UserPaymentTransactionException(String message) {
        super(message);
    }



}

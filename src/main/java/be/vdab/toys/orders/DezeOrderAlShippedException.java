package be.vdab.toys.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DezeOrderAlShippedException extends RuntimeException {
    public DezeOrderAlShippedException() {
        super("Deze order al Shipped");
    }
}

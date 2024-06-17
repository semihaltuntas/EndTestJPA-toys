package be.vdab.toys.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OnvoldoendeStockException extends RuntimeException {
    public OnvoldoendeStockException(String message) {
        super(message);
    }
}

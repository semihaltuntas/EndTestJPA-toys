package be.vdab.toys.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OnvoldoendeOrderException extends RuntimeException {

    public OnvoldoendeOrderException(String messeage) {
    super(messeage);
    }
}

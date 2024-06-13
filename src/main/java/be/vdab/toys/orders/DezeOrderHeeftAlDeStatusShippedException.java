package be.vdab.toys.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DezeOrderHeeftAlDeStatusShippedException extends RuntimeException {
    public DezeOrderHeeftAlDeStatusShippedException() {
        super("Deze order heeft al de status SHİPPED!");
    }
}

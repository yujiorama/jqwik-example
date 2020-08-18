package jqwik.example.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "not found")
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5831188421609220646L;
}

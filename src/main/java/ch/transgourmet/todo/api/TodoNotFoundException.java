package ch.transgourmet.todo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class TodoNotFoundException extends ErrorResponseException {

    public TodoNotFoundException(Integer id) {
        super(HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Todo with id %s not found".formatted(id)),
                null);
    }

}

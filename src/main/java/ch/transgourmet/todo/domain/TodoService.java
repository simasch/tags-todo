package ch.transgourmet.todo.domain;

import ch.transgourmet.todo.configuration.AppConfiguration;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final AppConfiguration appConfiguration;

    public TodoService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public String sayHello() {
        return "Hello: " + appConfiguration.getDescription();
    }

}

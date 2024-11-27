package ch.transgourmet.todo.domain;

import ch.transgourmet.todo.configuration.AppConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final AppConfiguration appConfiguration;

    public MyService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public String sayHello() {
        return "Hello: " + appConfiguration.getDescription();
    }
}

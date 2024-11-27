package ch.transgourmet.todo;

import ch.transgourmet.todo.domain.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoApplication.class);

    private final MyService myService;

    public TodoApplication(MyService myService) {
        this.myService = myService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOGGER.info(myService.sayHello());
    }
}

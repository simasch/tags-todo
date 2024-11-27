package ch.transgourmet.todo;

import ch.transgourmet.todo.domain.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoApplication.class);

    private final TodoService todoService;

    public TodoApplication(TodoService todoService) {
        this.todoService = todoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOGGER.info(todoService.sayHello());
    }
}

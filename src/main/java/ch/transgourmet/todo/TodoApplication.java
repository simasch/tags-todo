package ch.transgourmet.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}

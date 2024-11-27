package ch.transgourmet.todo.api;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("todos")
@RestController
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();

    @PostConstruct
    void addData() {
        todos.add(new Todo(1, "Einkaufen gehen", LocalDateTime.now(), false));
    }

    @GetMapping
    List<Todo> findAll() {
        return todos;
    }

    @GetMapping("{id}")
    Todo findById(@PathVariable Integer id) {
        return todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findAny()
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@RequestBody Todo todo) {
        todos.add(todo);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable Integer id) {
        if (todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findAny().isEmpty()) {
            throw new TodoNotFoundException(id);
        }

        todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findAny()
                .ifPresent(todos::remove);
    }
}
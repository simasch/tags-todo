package ch.transgourmet.todo.api;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Todo> findById(@PathVariable Integer id) {
        return todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findAny()
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@RequestBody Todo todo) {
        todos.add(todo);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findAny().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findAny()
                .ifPresent(todos::remove);
        return ResponseEntity.noContent().build();
    }
}
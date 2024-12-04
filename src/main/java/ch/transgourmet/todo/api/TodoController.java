package ch.transgourmet.todo.api;

import ch.transgourmet.todo.domain.Todo;
import ch.transgourmet.todo.domain.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("todos")
@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    List<TodoDTO> findAll() {
        return todoRepository.findAllAsDto();
    }

    @GetMapping("old")
    List<TodoDTO> findAllOld() {
        return todoRepository.findAll()
                .stream().map(todo -> new TodoDTO(todo.getId(), todo.getText(), todo.getCreatedAt(), todo.isDone()))
                .toList();
    }

    @GetMapping("{id}")
    TodoDTO findById(@PathVariable Integer id) {
        return todoRepository
                .findById(id)
                .map(todo -> new TodoDTO(todo.getId(), todo.getText(), todo.getCreatedAt(), todo.isDone()))
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@RequestBody TodoDTO todo) {
        var entity = new Todo(todo.text());
        todoRepository.save(entity);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable Integer id) {
        if (todoRepository.findById(id).isEmpty()) {
            throw new TodoNotFoundException(id);
        }

        todoRepository.deleteById(id);
    }
}
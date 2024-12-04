package ch.transgourmet.todo.domain;

import ch.transgourmet.todo.api.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("""
            select new ch.transgourmet.todo.api.TodoDTO(t.id, t.text, t.createdAt, t.done)
            from Todo t""")
    List<TodoDTO> findAllAsDto();
}

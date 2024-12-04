package ch.transgourmet.todo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Sql(statements = "insert into todo (id, text, created_at, done) values (10001, 'Buy milk', current_timestamp, false)")
    @Test
    void findAllAsDto() {
        var todos = todoRepository.findAllAsDto();

        assertThat(todos).hasSize(2);
    }

    @Test
    void save() {
        var todo = new Todo("Buy milk");

        var savedTodo = todoRepository.saveAndFlush(todo);

        assertThat(savedTodo.getId()).isNotNull();
        assertThat(savedTodo.getCreatedAt()).isNotNull();
    }
}
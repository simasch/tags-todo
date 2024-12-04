package ch.transgourmet.todo.domain;

import ch.transgourmet.todo.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@DataJpaTest(showSql = false)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Sql(statements = "insert into todo (id, text, created_at, done) values (1, 'Buy milk', current_timestamp, false)")
    @Test
    void findAllAsDto() {
        var todos = todoRepository.findAllAsDto();

        assertThat(todos).hasSize(1);
    }

    @Test
    void save() {
        var todo = new Todo("Buy milk");

        var savedTodo = todoRepository.saveAndFlush(todo);

        assertThat(savedTodo.getId()).isNotNull();
        assertThat(savedTodo.getCreatedAt()).isNotNull();
    }
}
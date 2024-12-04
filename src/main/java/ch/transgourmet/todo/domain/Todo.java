package ch.transgourmet.todo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;
    private String text;
    @CreatedDate
    private LocalDateTime createdAt;
    private boolean done;

    public Todo(String text) {
        this.text = text;
    }

    protected Todo() {
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        this.done = true;
    }
}

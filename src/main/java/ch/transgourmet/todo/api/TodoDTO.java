package ch.transgourmet.todo.api;

import java.time.LocalDateTime;

public record TodoDTO(Integer id, String text, LocalDateTime createdAt, boolean done) {
}

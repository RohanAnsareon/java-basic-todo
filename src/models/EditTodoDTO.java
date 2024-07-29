package models;

public record EditTodoDTO(
        String title,
        String description,
        TodoStatus status
) {
}

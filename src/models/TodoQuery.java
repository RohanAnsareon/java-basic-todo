package models;

public record TodoQuery (
    String search,
    Integer page,
    Integer per_page,
    TodoStatus status
) {}

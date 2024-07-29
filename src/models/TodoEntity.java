package models;

import java.time.LocalDateTime;

public record TodoEntity(
        int id,
        String title,
        String description,
        TodoStatus status,
        LocalDateTime created_at,
        LocalDateTime updated_at,
        LocalDateTime deleted_at
) {
    @Override
    public String toString() {

        return String.format(
                """
                        id: %s
                        title: %s
                        description: %s
                        status: %s
                        created_at: %s
                        updated_at: %s
                        deleted_at: %s
                        """,
                id,
                title,
                description.isBlank() || description.isEmpty() ? "Empty" : description,
                status,
                created_at.format(CommonValues.defaultFormat),
                updated_at != null ? updated_at.format(CommonValues.defaultFormat) : "N/A",
                deleted_at != null ? deleted_at.format(CommonValues.defaultFormat) : "N/A"
        );
    }
}

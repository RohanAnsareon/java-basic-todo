import models.EditTodoDTO;
import models.TodoEntity;
import models.TodoQuery;
import models.TodoStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoManager {
    private final List<TodoEntity> _todos = new ArrayList<>();
    private int _lastId = 0;

    public List<TodoEntity> getTodos(TodoQuery query) {
        final var per_page = query.per_page() != null ? query.per_page() : 20;
        final var page = query.page() != null ? query.page() : 1;
        final var search = query.search() != null ? query.search() : "";


        return this._todos.stream()
                .skip((long) (page - 1) * per_page)
                .limit(per_page)
                .filter(todo -> todo.title().contains(search) && (query.status() == null || todo.status() == query.status()))
                .toList();
    }

    public void createTodo(String title) {
        final var todo = new TodoEntity(
                ++this._lastId,
                title,
                "",
                TodoStatus.New,
                LocalDateTime.now(),
                null,
                null
        );
        this._todos.add(todo);
    }

    public void editTodo(int id, EditTodoDTO data) {
        List<TodoEntity> todos = this._todos;
        for (int i = 0; i < todos.size(); i++) {
            TodoEntity todo = todos.get(i);
            if (todo.id() == id) {
                final var newTodo = new TodoEntity(
                        todo.id(),
                        data.title() != null ? data.title() : todo.title(),
                        data.description() != null ? data.description() : todo.description(),
                        data.status() != null ? data.status() : todo.status(),
                        todo.created_at(),
                        LocalDateTime.now(),
                        null
                );

                this._todos.set(i, newTodo);
                break;
            }
        }
    }

    public void deleteTodo(int id) {
        this._todos.removeIf(todo -> todo.id() == id);
    }
}

import models.EditTodoDTO;
import models.TodoQuery;
import models.TodoStatus;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final var todoManager = new TodoManager();

        todoManager.createTodo("first");
        Thread.sleep(1000);
        todoManager.createTodo("second");
        Thread.sleep(2000);
        todoManager.createTodo("third");

        render(todoManager);

        Thread.sleep(2000);

        clearScreen();

        todoManager.editTodo(1, new EditTodoDTO(
                "first but edited",
                "description added finally",
                null
        ));

        todoManager.editTodo(2, new EditTodoDTO(
                "second but edited",
                null,
                TodoStatus.InProgress
        ));

        todoManager.editTodo(3, new EditTodoDTO(
                "third but edited",
                null,
                null
        ));

        render(todoManager);

        Thread.sleep(2000);

        clearScreen();

        todoManager.deleteTodo(1);
        todoManager.deleteTodo(3);
        render(todoManager);

    }

    private static void render(TodoManager manager) {
        System.out.println(manager.getTodos(new TodoQuery(
                null,
                null,
                null,
                null
        )));
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
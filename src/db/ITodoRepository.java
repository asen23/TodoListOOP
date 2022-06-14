package db;

import db.model.Todo;

import java.util.List;

public interface ITodoRepository {
    void addTodo(String title, String description);
    boolean updateTodo(int id, String title, String description);
    boolean updateTodo(int id, boolean isDone);
    boolean deleteTodo(int id);
    boolean addTag(int id, int tagId);
    boolean removeTag(int id, int tagId);
    Todo getTodoById(int id);
    List<Todo> getTodo();
    List<Todo> getTodoByTitleQuery(String query);
    List<Todo> getTodoByTagId(int id);
}

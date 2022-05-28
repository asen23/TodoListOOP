package db.repository;

import db.ITodoRepository;
import db.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements ITodoRepository {
    private ITodoRepository todoDb;
    private List<Todo> todos = null;

    public TodoRepository(ITodoRepository todoDb) {
        this.todoDb = todoDb;
    }
    @Override
    public void addTodo(String title, String description) {
        todos = null;
        todoDb.addTodo(title, description);
    }

    @Override
    public boolean updateTodo(int id, String title, String description) {
        todos = null;
        return todoDb.updateTodo(id, title, description);
    }

    @Override
    public boolean updateTodo(int id, boolean isDone) {
        todos = null;
        return todoDb.updateTodo(id, isDone);
    }

    @Override
    public boolean deleteTodo(int id) {
        todos = null;
        return todoDb.deleteTodo(id);
    }

    @Override
    public boolean addTag(int id, int tagId) {
        todos = null;
        return todoDb.addTag(id, tagId);
    }

    @Override
    public boolean removeTag(int id, int tagId) {
        todos = null;
        return todoDb.removeTag(id, tagId);
    }

    @Override
    public List<Todo> getTodo() {
        if(todos == null) {
            todos = todoDb.getTodo();
        }
        if(todos == null) {
            return new ArrayList<>();
        }
        return todos;
    }
}

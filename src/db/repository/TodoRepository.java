package db.repository;

import db.ITodoRepository;
import db.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements ITodoRepository {
    private final ITodoRepository todoDb;
    private List<Todo> todos = null;
    private Todo todo = null;
    private String lastQuery = null;
    private int lastTagId = -1;

    public TodoRepository(ITodoRepository todoDb) {
        this.todoDb = todoDb;
    }

    private void resetCache() {
        todos = null;
        todo = null;
        lastQuery = null;
        lastTagId = -1;
    }

    @Override
    public void addTodo(String title, String description) {
        resetCache();
        todoDb.addTodo(title, description);
    }

    @Override
    public boolean updateTodo(int id, String title, String description) {
        resetCache();
        return todoDb.updateTodo(id, title, description);
    }

    @Override
    public boolean updateTodo(int id, boolean isDone) {
        resetCache();
        return todoDb.updateTodo(id, isDone);
    }

    @Override
    public boolean deleteTodo(int id) {
        resetCache();
        return todoDb.deleteTodo(id);
    }

    @Override
    public boolean addTag(int id, int tagId) {
        resetCache();
        return todoDb.addTag(id, tagId);
    }

    @Override
    public boolean removeTag(int id, int tagId) {
        resetCache();
        return todoDb.removeTag(id, tagId);
    }

    @Override
    public Todo getTodoById(int id) {
        if(todo == null){
            todo = todoDb.getTodoById(id);
        }
        if(todo.getId() == id){
            return todo;
        } else {
            todo = todoDb.getTodoById(id);
        }
        return todo;
    }

    @Override
    public List<Todo> getTodo() {
        if (todos == null || lastTagId != -1 || lastQuery != null) {
            resetCache();
            todos = todoDb.getTodo();
            if (todos == null) {
                return new ArrayList<>();
            }
        }
        return todos;
    }

    @Override
    public List<Todo> getTodoByTitleQuery(String query) {
        if (!query.equals(lastQuery) || todos == null) {
            resetCache();
            todos = todoDb.getTodoByTitleQuery(query);
            lastQuery = query;
            if (todos == null) {
                return new ArrayList<>();
            }
        }
        return todos;
    }

    @Override
    public List<Todo> getTodoByTagId(int id) {
        if (lastTagId != id || todos == null) {
            resetCache();
            todos = todoDb.getTodoByTagId(id);
            lastTagId = id;
            if (todos == null) {
                return new ArrayList<>();
            }
        }
        return todos;
    }
}

package db.repository;

import db.ITodoRepository;
import db.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements ITodoRepository {
	private final ITodoRepository todoDb;
	private List<Todo> todos = null;
	private String lastQuery = null;
	private int lastTagId = -1;

	public TodoRepository(ITodoRepository todoDb) {
		this.todoDb = todoDb;
	}

	private void setNeedUpdate() {
		todos = null;
		lastQuery = null;
		lastTagId = -1;
	}

	@Override
	public void addTodo(String title, String description) {
		setNeedUpdate();
		todoDb.addTodo(title, description);
	}

	@Override
	public boolean updateTodo(int id, String title, String description) {
		setNeedUpdate();
		return todoDb.updateTodo(id, title, description);
	}

	@Override
	public boolean updateTodo(int id, boolean isDone) {
		setNeedUpdate();
		return todoDb.updateTodo(id, isDone);
	}

	@Override
	public boolean deleteTodo(int id) {
		setNeedUpdate();
		return todoDb.deleteTodo(id);
	}

	@Override
	public boolean addTag(int id, int tagId) {
		setNeedUpdate();
		return todoDb.addTag(id, tagId);
	}

	@Override
	public boolean removeTag(int id, int tagId) {
		setNeedUpdate();
		return todoDb.removeTag(id, tagId);
	}

	@Override
	public List<Todo> getTodo() {
		if (todos == null || lastTagId != -1 || lastQuery != null) {
			setNeedUpdate();
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
			setNeedUpdate();
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
			setNeedUpdate();
			todos = todoDb.getTodoByTagId(id);
			lastTagId = id;
			if (todos == null) {
				return new ArrayList<>();
			}
		}
		return todos;
	}
}

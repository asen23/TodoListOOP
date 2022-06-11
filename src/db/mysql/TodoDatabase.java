package db.mysql;

import db.Connect;
import db.ITodoRepository;
import db.model.Tag;
import db.model.Todo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDatabase implements ITodoRepository {
	private final Connect db = Connect.getInstance();

	@Override
	public void addTodo(String title, String description) {
		try (PreparedStatement ps = db.prepareStatement("INSERT INTO todo(title, description) " + "VALUES(?, ?)")) {
			ps.setString(1, title);
			ps.setString(2, description);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean isTodoNotExist(int id) {
		try (PreparedStatement ps = db.prepareStatement("SELECT * FROM todo " + "WHERE id = ?")) {
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			return !result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateTodo(int id, String title, String description) {
		if (isTodoNotExist(id)) {
			return false;
		}
		try (PreparedStatement ps = db
				.prepareStatement("UPDATE todo " + "SET title = ?, description = ? " + "WHERE id = ?")) {
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateTodo(int id, boolean isDone) {
		if (isTodoNotExist(id)) {
			return false;
		}
		try (PreparedStatement ps = db.prepareStatement("UPDATE todo " + "SET is_done = ? " + "WHERE id = ?")) {
			ps.setBoolean(1, isDone);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteTodo(int id) {
		if (isTodoNotExist(id)) {
			return false;
		}
		try (PreparedStatement ps = db.prepareStatement("DELETE FROM todo " + "WHERE id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isTagNotExist(int id) {
		try (PreparedStatement ps = db.prepareStatement("SELECT * FROM tag " + "WHERE id = ?")) {
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			return !result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean isTodoTagExist(int id, int tagId) {
		try (PreparedStatement ps = db
				.prepareStatement("SELECT * FROM todo_tag " + "WHERE todo_id = ? AND tag_id = ?")) {
			ps.setInt(1, id);
			ps.setInt(2, tagId);
			ResultSet result = ps.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addTag(int id, int tagId) {
		if (isTodoNotExist(id)) {
			return false;
		}
		if (isTagNotExist(tagId)) {
			return false;
		}
		if (isTodoTagExist(id, tagId)) {
			return false;
		}
		try (PreparedStatement ps = db.prepareStatement("INSERT INTO todo_tag(todo_id, tag_id) " + "VALUES(?, ?)")) {
			ps.setInt(1, id);
			ps.setInt(2, tagId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean removeTag(int id, int tagId) {
		if (isTodoNotExist(id)) {
			return false;
		}
		if (isTagNotExist(tagId)) {
			return false;
		}
		if (!isTodoTagExist(id, tagId)) {
			return false;
		}
		try (PreparedStatement ps = db.prepareStatement("DELETE FROM todo_tag " + "WHERE todo_id = ? AND tag_id = ?")) {
			ps.setInt(1, id);
			ps.setInt(2, tagId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private List<Todo> getTodoWithPreparedStatement(PreparedStatement ps) {
		ArrayList<Todo> todos = new ArrayList<>();
		try (ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				int id = result.getInt("id");
				String title = result.getString("title");
				String description = result.getString("description");
				boolean isDone = result.getBoolean("is_done");
				todos.add(new Todo(id, title, description, isDone, null));
			}
			for (Todo todo : todos) {
				ArrayList<Tag> tags = new ArrayList<>();
				try (PreparedStatement tps = db.prepareStatement(
						"SELECT id, name FROM todo_tag " + "JOIN tag ON id = tag_id " + "WHERE todo_id = ?")) {
					tps.setInt(1, todo.getId());
					ResultSet tagResult = tps.executeQuery();
					while (tagResult.next()) {
						int tagId = tagResult.getInt("id");
						String name = tagResult.getString("name");
						tags.add(new Tag(tagId, name));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				todo.setTags(tags);
			}
			return todos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Todo> getTodo() {
		try (PreparedStatement ps = db.prepareStatement("SELECT * FROM todo")) {
			return getTodoWithPreparedStatement(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Todo> getTodoByTitleQuery(String titleQuery) {
		try (PreparedStatement ps = db.prepareStatement("SELECT * FROM todo WHERE title LIKE ?")) {
			ps.setString(1, "%" + titleQuery + "%");
			return getTodoWithPreparedStatement(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Todo> getTodoByTagId(int id) {
		try (PreparedStatement ps = db
				.prepareStatement("SELECT * FROM todo " + "JOIN todo_tag ON todo_id = id " + "WHERE tag_id = ?")) {
			ps.setInt(1, id);
			return getTodoWithPreparedStatement(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

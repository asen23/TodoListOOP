package db.mysql;

import db.Connect;
import db.ITodoRepository;
import db.model.Tag;
import db.model.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDatabase implements ITodoRepository {
    private final Connect db = Connect.getInstance();

    @Override
    public void addTodo(String title, String description) {
        String query = String.format(
              "INSERT INTO todo(title, description) " +
              "VALUES('%s', '%s')",
              title, description
        );
        db.executeUpdate(query);
    }

    private boolean isTodoNotExist(int id) {
        try {
            String query = String.format(
                  "SELECT * FROM todo " +
                  "WHERE id = %d",
                  id
            );
            ResultSet result = db.executeQuery(query);
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
        String query = String.format(
              "UPDATE todo " +
              "SET title = '%s', description = '%s' " +
              "WHERE id = %d",
              title, description, id
        );
        db.executeUpdate(query);
        return true;
    }

    @Override
    public boolean updateTodo(int id, boolean isDone) {
        if (isTodoNotExist(id)) {
            return false;
        }
        String query = String.format(
              "UPDATE todo " +
              "SET is_done = %b " +
              "WHERE id = %d",
              isDone, id
        );
        db.executeUpdate(query);
        return true;
    }

    @Override
    public boolean deleteTodo(int id) {
        if (isTodoNotExist(id)) {
            return false;
        }
        String query = String.format(
              "DELETE FROM todo " +
              "WHERE id = %d",
              id
        );
        db.executeUpdate(query);
        return false;
    }

    private boolean isTagNotExist(int id) {
        try {
            String query = String.format(
                  "SELECT * FROM tag " +
                  "WHERE id = %d",
                  id
            );
            ResultSet result = db.executeQuery(query);
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean isTodoTagExist(int id, int tagId) {
        try {
            String query = String.format(
                  "SELECT * FROM todo_tag " +
                  "WHERE todo_id = %d AND tag_id = %d",
                  id, tagId
            );
            ResultSet result = db.executeQuery(query);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addTag(int id, int tagId) {
        if(isTodoNotExist(id)) {
            return false;
        }
        if(isTagNotExist(tagId)) {
            return false;
        }
        if(isTodoTagExist(id, tagId)) {
            return false;
        }
        String query = String.format(
              "INSERT INTO todo_tag(todo_id, tag_id) " +
              "VALUES('%s', '%s')",
              id, tagId
        );
        db.executeUpdate(query);
        return true;
    }

    @Override
    public boolean removeTag(int id, int tagId) {
        if(isTodoNotExist(id)) {
            return false;
        }
        if(isTagNotExist(tagId)) {
            return false;
        }
        if(!isTodoTagExist(id, tagId)) {
            return false;
        }
        String query = String.format(
              "DELETE FROM todo_tag " +
              "WHERE todo_id = %d AND tag_id = %d",
              id, tagId
        );
        db.executeUpdate(query);
        return true;
    }

    @Override
    public List<Todo> getTodo() {
        try {
            ArrayList<Todo> todos = new ArrayList<>();
            String query = "SELECT * FROM todo";
            ResultSet result = db.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                boolean isDone = result.getBoolean("is_done");
                todos.add(new Todo(id, title, description, isDone, null));
            }
            for (Todo todo : todos) {
                String tagQuery = String.format(
                      "SELECT id, name FROM todo_tag " +
                      "JOIN tag ON id = tag_id " +
                      "WHERE todo_id = %d",
                      todo.getId()
                );
                ResultSet tagResult = db.executeQuery(tagQuery);
                ArrayList<Tag> tags = new ArrayList<>();
                while (tagResult.next()) {
                    int tagId = tagResult.getInt("id");
                    String name = tagResult.getString("name");
                    tags.add(new Tag(tagId, name));
                }
                todo.setTags(tags);
            }
            return todos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

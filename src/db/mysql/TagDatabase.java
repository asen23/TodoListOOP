package db.mysql;

import db.Connect;
import db.ITagRepository;
import db.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDatabase implements ITagRepository {
    private final Connect db = Connect.getInstance();

    @Override
    public void addTag(String name) {
        String query = String.format(
              "INSERT INTO tag(name) " +
              "VALUES('%s')",
              name
        );
        db.executeUpdate(query);
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

    @Override
    public boolean updateTag(int id, String name) {
        if (isTagNotExist(id)) {
            return false;
        }
        String query = String.format(
              "UPDATE tag " +
              "SET name = '%s' " +
              "WHERE id = %d",
              name, id
        );
        db.executeUpdate(query);
        return true;
    }

    @Override
    public boolean deleteTag(int id) {
        if (isTagNotExist(id)) {
            return false;
        }
        String query = String.format(
              "DELETE FROM tag " +
              "WHERE id = %d",
              id
        );
        db.executeUpdate(query);
        return false;
    }

    @Override
    public List<Tag> getTags() {
        try {
            ArrayList<Tag> tags = new ArrayList<>();
            String query = "SELECT * FROM tag";
            ResultSet result = db.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                tags.add(new Tag(id, name));
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

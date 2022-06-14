package db.mysql;

import db.ITagRepository;
import db.model.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDatabase extends Database implements ITagRepository {

    @Override
    public void addTag(String name) {
        try (PreparedStatement ps = db.prepareStatement(
              "INSERT INTO tag(name) " +
              "VALUES(?)"
        )) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isTagNotExist(int id) {
        try (PreparedStatement ps = db.prepareStatement(
              "SELECT * FROM tag " +
              "WHERE id = ?"
        )) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Tag getTagById(int id) {
        if (isTagNotExist(id)) {
            return null;
        }
        try (PreparedStatement ps = db.prepareStatement(
              "SELECT * FROM tag " +
              "WHERE id = ?"
        )) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            int tagId = result.getInt("id");
            String name = result.getString("name");
            return new Tag(tagId, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateTag(int id, String name) {
        if (isTagNotExist(id)) {
            return false;
        }
        try (PreparedStatement ps = db.prepareStatement(
              "UPDATE tag " +
              "SET name = ? " +
              "WHERE id = ?"
        )) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteTag(int id) {
        if (isTagNotExist(id)) {
            return false;
        }
        try (PreparedStatement ps = db.prepareStatement(
              "DELETE FROM tag " +
              "WHERE id = ?"
        )) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Tag> getTags() {
        ArrayList<Tag> tags = new ArrayList<>();
        try (PreparedStatement ps = db.prepareStatement(
              "SELECT * FROM tag"
        )) {
            ResultSet result = ps.executeQuery();
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

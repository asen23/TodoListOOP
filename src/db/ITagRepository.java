package db;

import db.model.Tag;
import java.util.List;

public interface ITagRepository {
	void addTag(String name);
	Tag getTagById(int id);
	boolean updateTag(int id, String name);
	boolean deleteTag(int id);
	List<Tag> getTags();
}
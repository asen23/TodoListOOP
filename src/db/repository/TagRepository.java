package db.repository;

import db.ITagRepository;
import db.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagRepository implements ITagRepository {
	private final ITagRepository tagDb;
	private List<Tag> tags = null;
	private Tag tag = null;

	public TagRepository(ITagRepository tagDb) {
		this.tagDb = tagDb;
	}

	private void resetCache() {
		tags = null;
		tag = null;
	}

	@Override
	public void addTag(String name) {
		resetCache();
		tagDb.addTag(name);
	}

	@Override
	public Tag getTagById(int id) {
		if (tag == null) {
			tag = tagDb.getTagById(id);
		}
		if (tag.getId() == id) {
			return tag;
		} else {
			tag = tagDb.getTagById(id);
		}
		return tag;
	}

	@Override
	public boolean updateTag(int id, String name) {
		resetCache();
		return tagDb.updateTag(id, name);
	}

	@Override
	public boolean deleteTag(int id) {
		resetCache();
		return tagDb.deleteTag(id);
	}

	@Override
	public List<Tag> getTags() {
		if (tags == null) {
			tags = tagDb.getTags();
		}
		if (tags == null) {
			return new ArrayList<>();
		}
		return tags;
	}
}

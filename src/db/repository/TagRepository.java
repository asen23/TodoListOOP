package db.repository;

import db.ITagRepository;
import db.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagRepository implements ITagRepository {
    private final ITagRepository tagDb;
    private List<Tag> tags = null;

    public TagRepository(ITagRepository tagDb) {
        this.tagDb = tagDb;
    }

    @Override
    public void addTag(String name) {
        tags = null;
        tagDb.addTag(name);
    }

    @Override
    public boolean updateTag(int id, String name) {
        tags = null;
        return tagDb.updateTag(id, name);
    }

    @Override
    public boolean deleteTag(int id) {
        tags = null;
        return tagDb.deleteTag(id);
    }

    @Override
    public List<Tag> getTags() {
        if(tags == null) {
            tags = tagDb.getTags();
        }
        if(tags == null) {
            return new ArrayList<>();
        }
        return tags;
    }
}

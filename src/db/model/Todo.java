package db.model;

import java.util.List;

public class Todo {
	private final int id;
	private final String title;
	private final String description;
	private final boolean isDone;
	private List<Tag> tags;

	public Todo(int id, String title, String description, boolean isDone, List<Tag> tags) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.isDone = isDone;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDone() {
		return isDone;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}

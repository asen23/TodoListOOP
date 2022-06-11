package di;

import db.ITagRepository;
import db.ITodoRepository;
import db.mysql.TagDatabase;
import db.mysql.TodoDatabase;
import db.repository.TagRepository;
import db.repository.TodoRepository;

public class Injection {
	public static ITodoRepository getTodoRepository() {
		ITodoRepository db = new TodoDatabase();
		return new TodoRepository(db);
	}

	public static ITagRepository getTagRepository() {
		ITagRepository db = new TagDatabase();
		return new TagRepository(db);
	}
}

package menu;

import java.util.ArrayList;
import java.util.Scanner;

import db.ITagRepository;
import db.ITodoRepository;
import db.model.Tag;
import db.model.Todo;
import di.Injection;
import helper.PrintHelp;

public class ToDoMenuChoice {
	Scanner scan = new Scanner(System.in);
	PrintHelp ph = PrintHelp.getHelp();
	ArrayList<Todo> todos = new ArrayList<>();
	ITodoRepository td = Injection.getTodoRepository();
	ITagRepository tg = Injection.getTagRepository();
	TagMenuChoice tag = new TagMenuChoice();

	protected void viewTodos() {
		ph.printEnter();
		int i = 1;
		todos = (ArrayList<Todo>) td.getTodo();
		ph.printSeparator();
		System.out.println("List of Todos");
		ph.printSeparator();
		for (Todo x : todos) {
			i++;
			System.out.println(i + ".");
			System.out.println("ID          = " + x.getId());
			System.out.println("Title       = " + x.getTitle());
			System.out.println("Description = " + x.getDescription());
			System.out.print("Tag         = ");
			for (Tag y : x.getTags()) {
				System.out.println(y.getName() + " ");
			}
			ph.printEnter();
		}
		ph.printSeparator();
		ph.pressEnter();
		ph.printEnter();
	}

	protected void addTodo() {
		ph.printEnter();
		String title, description;
		System.out.print("Title       = ");
		title = scan.nextLine();
		System.out.print("Description = ");
		description = scan.nextLine();
		td.addTodo(title, description);
		System.out.println("Congrats, an item successfully added to your toDo List");
		ph.printEnter();
	}

	protected void editTodo() {
		ph.printEnter();
		viewEditTodoList();
		int choice = scan.nextInt();
		scan.nextLine();
		int id = getTodoId();
		if (choice == 1) {
			boolean isDone = scan.nextBoolean();
			if (td.updateTodo(id, isDone)) {
				System.out.println("Congrats, item's status successfully edited in your Todo List");
			} else {
				System.out.println("Edit process failed..");
			}
		} else {
			System.out.print("Title       = ");
			String title = scan.nextLine();
			System.out.print("Description = ");
			String description = scan.nextLine();
			if (td.updateTodo(id, title, description)) {
				System.out.println("Congrats, item's detail successfully edited in your Todo List");
			} else {
				System.out.println("Edit process failed..");
			}
		}
		ph.pressEnter();
		ph.printEnter();
	}

	private void viewEditTodoList() {
		ph.printSeparator();
		System.out.println("List of Edit Todo List Choice");
		ph.printSeparator();
		System.out.println("1. Edit Status");
		System.out.println("2. Edit Content");
		ph.printSeparator();
		System.out.print(">>");
	}

	protected void deleteTodo() {
		ph.printEnter();
		viewTodos();
		int id = getTodoId();
		if (td.deleteTodo(id)) {
			System.out.println("Congrats, an item successfully deleted from your Todo List");
		} else {
			System.out.println("Deletion has failed..");
		}
		viewTodos();
		ph.pressEnter();
		ph.printEnter();
	}

	protected int getTodoId() {
		int choice;
		do {
			System.out.print("ID          = ");
			choice = scan.nextInt();
			scan.nextLine();
			if (td.getTodoById(choice) == null) {
				System.out.println("Id is not valid! please enter again!");
			} else {
				break;
			}
		} while (true);
		return choice;
	}

	protected int getTagId() {
		int choice;
		do {
			System.out.print("TagID       = ");
			choice = scan.nextInt();
			scan.nextLine();
			if (tg.getTagById(choice) == null) {
				System.out.println("Id is not valid! please enter again!");
			} else {
				break;
			}
		} while (true);
		return choice;
	}

	protected void addTagInTodo() {
		ph.printEnter();
		tag.viewTags();
		int id = getTodoId();
		int tagId = getTagId();
		if(td.addTag(id, tagId)){
			System.out.println("Congrats, a Tag successfully linked with a Todo");
		}
		else {
			System.out.println("a Tag failed to link with a todo..");
		}
		ph.pressEnter();
		ph.printEnter();
	}

	protected void deleteTagInTodo() {
		ph.printEnter();
		tag.viewTags();
		int id = getTodoId();
		int tagId = getTagId();
		if(td.removeTag(id, tagId)) {
			System.out.println("Congrats, a Tag successfully removed from a Todo");
		}
		else {
			System.out.println("a tag failed to removed from a Todo..");
		}
		ph.pressEnter();
		ph.printEnter();
	}
}

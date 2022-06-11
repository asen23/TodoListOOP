package menu;

import java.util.ArrayList;
import java.util.Scanner;

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
	TagMenuChoice tag = new TagMenuChoice();

	protected void viewTodos() {
		int i = 1;
		todos = (ArrayList<Todo>) td.getTodo();
		ph.printBlankPage();
		ph.printSeparator();
		System.out.println("List of Todos");
		ph.printSeparator();
		for (Todo x : todos) {
			i++;
			System.out.println(i);
			System.out.println();
			System.out.println("ID          = " + x.getId());
			System.out.println("Title       = " + x.getTitle());
			System.out.println("Description = " + x.getDescription());
			for (Tag y : x.getTags()) {
				System.out.println("Tag         = " + y.getName());
			}
			ph.printEnter();
		}
		ph.printSeparator();
		ph.pressEnter();
	}

	protected void addTodo() {
		String title, description;
		System.out.print("Title       = ");
		title = scan.nextLine();
		System.out.print("Description = ");
		description = scan.nextLine();
		td.addTodo(title, description);
		System.out.println("Congrats, an item successfully added to your toDo List");
		viewTodos();
	}

	protected void editTodo() {
		viewEditTodoList();
		int choice = scan.nextInt();
		System.out.print("ID          = ");
		int id = scan.nextInt();
		scan.nextLine();
		if (choice == 1) {
			boolean isDone = scan.nextBoolean();
			td.updateTodo(id, isDone);
		} else {
			System.out.print("Title       = ");
			String title = scan.nextLine();
			System.out.print("Description = ");
			String description = scan.nextLine();
			td.updateTodo(id, title, description);
		}
		System.out.println("Congrats, an item successfully edited in your Todo List");
		viewTodos();
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
		int id;
		viewTodos();
		System.out.print("Select Todo ID to delete");
		id = scan.nextInt();
		scan.nextLine();
		td.deleteTodo(id);
		System.out.println("Congrats, an item successfully deleted from your Todo List");
		viewTodos();
	}

	protected void addTagInTodo() {
		tag.viewTags();
		System.out.print("ID             = ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.print("TagID          = ");
		int tagId = scan.nextInt();
		scan.nextLine();
		td.addTag(id, tagId);
		System.out.println("Congrats, an Tag successfully linked with a Todo");
		ph.pressEnter();
	}

	protected void deleteTagInTodo() {
		tag.viewTags();
		System.out.print("ID             = ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.print("TagID          = ");
		int tagId = scan.nextInt();
		scan.nextLine();
		td.removeTag(id, tagId);
		System.out.println("Congrats, a Tag successfully removed from a Todo");
		ph.pressEnter();
	}
}

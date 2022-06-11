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

	protected void viewTodos() {

		todos = (ArrayList<Todo>) td.getTodo();

		for (Todo x : todos) {
			System.out.println("ID          = " + x.getId());
			System.out.println("Title       = " + x.getTitle());
			System.out.println("Description = " + x.getDescription());
			for (Tag y : x.getTags()) {
				System.out.println("Tag         = " + y.getName());
			}
			ph.printEnter();
		}
		ph.pressEnter();
		ph.printBlankPage();
	}

	protected void addTodo() {
		String title, description;
		System.out.print("Title       = ");
		title = scan.nextLine();
		System.out.print("Description = ");
		description = scan.nextLine();
		td.addTodo(title, description);
		System.out.println("Congrats, an item is added to your toDo List");

		viewTodos();
	}
}

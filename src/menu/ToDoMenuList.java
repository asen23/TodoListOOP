package menu;

import java.util.Scanner;

import helper.PrintHelp;

public class ToDoMenuList implements IListing {
	PrintHelp ph = PrintHelp.getHelp();
	Scanner scan = new Scanner(System.in);

	protected void MenuList() {
		int choice = -1;
		ToDoMenuChoice option = new ToDoMenuChoice();

		do {
			printMenuList();
			while (scan.hasNext()) {
				if (!scan.hasNextInt()) {
					scan.nextLine();
					continue;
				}
				choice = scan.nextInt();
				scan.nextLine();
				break;
			}
			switch (choice) {
			case 1:
				option.viewTodos();
				break;
			case 2:
				option.addTodo();
				break;
			case 3:
//                    editTodo();
				break;
			case 4:
//                    deleteTodo();
				break;
			case 0:
				break;
			default:
				System.out.println("Invalid Input, please try again");
				ph.pressEnter();
				ph.printBlankPage();
			}
		} while (choice != 0);
	}

	@Override
	public void printMenuList() {
		ph.printSeparator();
		System.out.println("1. View Todo list");
		System.out.println("2. Add a Todo");
		System.out.println("3. Edit a Todo");
		System.out.println("4. Delete a Todo");
		System.out.println("0. Back to main menu");
		ph.printSeparator();
	}
}

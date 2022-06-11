package menu;

import java.util.Scanner;

import helper.PrintHelp;

public class Menu {
	PrintHelp ph = PrintHelp.getHelp();
	Scanner scan = new Scanner(System.in);
	ToDoMenu toDo = new ToDoMenu();
	TagMenu tag = new TagMenu();

	public Menu() {
		int choice;
		do {
			ph.printSeparator();
			System.out.println("Main Menu");
			System.out.println("1. Todo Menu");
			System.out.println("2. Tag Menu");
			System.out.println("0. Quit Program");
			ph.printSeparator();
			choice = scan.nextInt();
			scan.nextLine();
			ph.printBlankPage();

			switch (choice) {
			case 1:
				toDo.MenuList();
				break;
			case 2:
				tag.MenuList();
				break;
			case 0:
				scan.close();
				break;
			default:
				System.out.println("Invalid Input, please try again");
				ph.pressEnter();
				ph.printBlankPage();
			}
		} while (choice != 0);
	}

}
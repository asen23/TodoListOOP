package menu;

import java.util.Scanner;

import helper.PrintHelp;

public class TagMenu implements IListing {
	PrintHelp ph = PrintHelp.getHelp();
	Scanner scan = new Scanner(System.in);

	protected void MenuList() {
		int choice = -1;
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
//                    viewTags();
				break;
			case 2:
//                    addTag();
				break;
			case 3:
//                    editTag();
				break;
			case 4:
//                    deleteTag();
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
		System.out.println("1. View Tag list ");
		System.out.println("2. Add a Tag");
		System.out.println("3. Edit a Tag");
		System.out.println("4. Delete a Tag");
		System.out.println("0. Back to main menu");
		ph.printSeparator();
	}
}

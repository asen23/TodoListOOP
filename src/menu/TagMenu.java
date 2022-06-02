package menu;

import java.util.Scanner;

import helper.PrintHelp;

public class TagMenu implements IListing {
	static PrintHelp ph = new PrintHelp();
	Scanner scan = new Scanner(System.in);
	
	protected void tagMenu() {
        int choice;
        do {
        	printMenuList();
            choice = scan.nextInt();
            scan.nextLine();
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

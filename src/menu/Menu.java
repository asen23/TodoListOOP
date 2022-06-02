package menu;

import java.util.Scanner;

public class Menu {
    Scanner scan = new Scanner(System.in);

    public Menu() {
        int choice;
        do {
            printSeparator();
            System.out.println("Main Menu");
            System.out.println("1. Todo Menu");
            System.out.println("2. Tag Menu");
            System.out.println("0. Quit Program");
            printSeparator();
            choice = scan.nextInt();
            scan.nextLine();
            printBlankPage();

            switch (choice) {
                case 1:
                    todoMenu();
                    break;
                case 2:
                    tagMenu();
                    break;
                case 0:
                    scan.close();
                    break;
                default:
                    System.out.println("Invalid Input, please try again");
                    pressEnter();
                    printBlankPage();
            }


        } while (choice != 0);
    }

    private void todoMenu() {
        int choice;

        do {
            printTodoMenu();
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
//                    viewTodos();
                    break;
                case 2:
//                    addTodo();
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
                    pressEnter();
                    printBlankPage();
            }
        } while (choice != 0);
    }

    private void tagMenu() {
        int choice;
        do {
            printTagsMenu();
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
                    pressEnter();
                    printBlankPage();
            }
        } while (choice != 0);
    }

    private void printTodoMenu() {
        printSeparator();
        System.out.println("1. View Todo list");
        System.out.println("2. Add a Todo");
        System.out.println("3. Edit a Todo");
        System.out.println("4. Delete a Todo");
        System.out.println("0. Back to main menu");
        printSeparator();
    }

    private void printTagsMenu() {
        printSeparator();
        System.out.println("1. View Tag list ");
        System.out.println("2. Add a Tag");
        System.out.println("3. Edit a Tag");
        System.out.println("4. Delete a Tag");
        System.out.println("0. Back to main menu");
        printSeparator();
    }

    public void printSeparator() {
        System.out.println("=========================");
    }

    public void printBlankPage() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public void pressEnter() {
        System.out.println("Press Enter to Continue");
        scan.nextLine();
    }


}

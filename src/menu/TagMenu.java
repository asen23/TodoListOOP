package menu;

import java.util.List;
import java.util.Scanner;

import db.ITagRepository;
import db.model.Tag;
import di.Injection;
import helper.PrintHelp;

public class TagMenu implements IListing {
    PrintHelp ph = PrintHelp.getHelp();
    Scanner scan = new Scanner(System.in);
    ITagRepository tag = Injection.getTagRepository();

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
                    viewTags();
                    break;
                case 2:
                    addTag();
                    break;
                case 3:
                    editTag();
                    break;
                case 4:
                    deleteTag();
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
        ph.printBlankPage();
        ph.printSeparator();
        System.out.println("1. View Tag list ");
        System.out.println("2. Add a Tag");
        System.out.println("3. Edit a Tag");
        System.out.println("4. Delete a Tag");
        System.out.println("0. Back to main menu");
        ph.printSeparator();
    }

    private void viewTags() {
        ph.printBlankPage();
        ph.printSeparator();
        System.out.println("List of Tags");
        print(tag.getTags());
        ph.pressEnter();
    }

    private void addTag() {
        ph.printBlankPage();
        String name = getTagName();
        tag.addTag(name);
        System.out.printf("%s has been added to tags database!\n", name);
        ph.pressEnter();
    }

    private void editTag() {
        ph.printBlankPage();
        print(tag.getTags());
        int tagId = getTagId("Choose tag id to edit");
        String tagName = getTagName();
        System.out.printf("Changing %s to %s..\n", tag.getTagById(tagId).getName(), tagName);
        if (tag.updateTag(tagId, tagName)) {
            System.out.println("Edit has been successful!");
        } else {
            System.out.println("Edit has failed..");
        }
        ph.pressEnter();

    }

    private void deleteTag() {
        ph.printBlankPage();
        print(tag.getTags());
        int tagId = getTagId("Choose tag id to delete");
        System.out.printf("Deleting %s..\n", tag.getTagById(tagId).getName());
        if (tag.deleteTag(tagId)) {
            System.out.println("Tag has been deleted successfully!");
        } else {
            System.out.println("Deletion has failed..");
        }
        ph.pressEnter();
    }

    private int getTagId(String sentence) {
        int choice;
        do {
            System.out.printf("%s\n", sentence);
            choice = scan.nextInt();
            scan.nextLine();
            if (tag.getTagById(choice) == null) {
                System.out.println("Id is not valid! please enter again!");
            } else {
                break;
            }
        } while (true);
        return choice;
    }

    private String getTagName() {
        String name;
        do {
            System.out.println("Input a new tag [1..15]");
            name = scan.nextLine();

            if (name.length() < 1 || name.length() > 15) {
                System.out.println("Please enter a valid tag!");
            } else {
                break;
            }
        } while (true);
        return name;
    }

    private void print(List<Tag> tags) {
        ph.printSeparator();
        for (Tag tag : tags) {
            System.out.printf("%d. %s\n", tag.getId(), tag.getName());
        }
        ph.printSeparator();
    }


}

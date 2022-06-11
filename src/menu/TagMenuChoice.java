package menu;

import java.util.List;
import java.util.Scanner;

import db.ITagRepository;
import db.model.Tag;
import di.Injection;
import helper.PrintHelp;

public class TagMenuChoice {
	PrintHelp ph = PrintHelp.getHelp();
    Scanner scan = new Scanner(System.in);
    ITagRepository tag = Injection.getTagRepository();
    
    protected void viewTags() {
        ph.printBlankPage();
        ph.printSeparator();
        System.out.println("List of Tags");
        print(tag.getTags());
        ph.pressEnter();
    }

    protected void addTag() {
        ph.printBlankPage();
        String name = getTagName();
        tag.addTag(name);
        System.out.printf("%s has been added to tags database!\n", name);
        ph.pressEnter();
    }

    protected void editTag() {
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

    protected void deleteTag() {
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

    protected int getTagId(String sentence) {
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

    protected String getTagName() {
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

    protected void print(List<Tag> tags) {
        ph.printSeparator();
        for (Tag tag : tags) {
            System.out.printf("%d. %s\n", tag.getId(), tag.getName());
        }
        ph.printSeparator();
    }

}

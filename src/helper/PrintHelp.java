package helper;

import java.util.Scanner;

public class PrintHelp {
	Scanner scan = new Scanner(System.in);
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

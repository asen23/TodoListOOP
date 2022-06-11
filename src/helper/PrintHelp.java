package helper;

import java.util.Scanner;

public class PrintHelp {
	Scanner scan = new Scanner(System.in);

	private static PrintHelp helper = new PrintHelp();

	private PrintHelp() {

	}

	public static PrintHelp getHelp() {
		return helper;
	}

	public void printSeparator() {
		System.out.println("=========================");
	}

	public void printBlankPage() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	public void printEnter() {
		System.out.println();
	}

	public void pressEnter() {
		System.out.println("Press Enter to Continue");
		scan.nextLine();
	}
}

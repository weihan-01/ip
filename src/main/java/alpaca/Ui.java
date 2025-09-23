package alpaca;

import java.util.Scanner;

/**
 * Handles all user interaction, including input and output.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor to initialize the Scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Alpaca 🦙");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message when loading fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Reads the next command from the user.
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon! 🌈");
        showLine();
    }

    /**
     * Prints a custom message surrounded by lines.
     * @param msg The message to print.
     */
    public void showMessage(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }

    /**
     * Closes the Scanner to prevent resource leaks.
     */
    public void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Alpaca 🦙");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon! 🌈");
        showLine();
    }

    public void showMessage(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }
}
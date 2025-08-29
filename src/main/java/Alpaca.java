import java.util.Scanner;

public class Alpaca {

    private static final String HR = "____________________________________________________________";

    private static void printBox(String... lines) {
        System.out.println(HR);
        for (String line : lines) {
            System.out.println(" " + line);
        }
        System.out.println(HR);
    }

    public static void main(String[] args) {
        // Greeting
        printBox(
            "Hello! I'm Alpaca",
            "What can I do for you?"
        );

        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!sc.hasNextLine()) break;          // handles EOF (e.g., Ctrl+Z/Ctrl+D)
            String input = sc.nextLine();

            if ("bye".equals(input)) {              // exit on exact "bye"
                printBox("Bye. Hope to see you again soon!");
                break;
            }

            // Echo the command
            printBox(input);
        }
        sc.close();
    }
}

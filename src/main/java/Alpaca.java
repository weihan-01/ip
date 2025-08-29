import java.util.ArrayList;
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
        ArrayList<String> tasks = new ArrayList<>();

        // Greeting
        printBox(
            "Hello! I'm Alpaca",
            "What can I do for you?"
        );

        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!sc.hasNextLine()) break;
            String input = sc.nextLine();

            if ("bye".equals(input)) {
                printBox("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(input)) {
                // Print all tasks
                if (tasks.isEmpty()) {
                    printBox("No tasks added yet.");
                } else {
                    String[] output = new String[tasks.size()];
                    for (int i = 0; i < tasks.size(); i++) {
                        output[i] = (i + 1) + ". " + tasks.get(i);
                    }
                    printBox(output);
                }
            } else {
                // Add any other input as a task
                tasks.add(input);
                printBox("added: " + input);
            }
        }
        sc.close();
    }
}

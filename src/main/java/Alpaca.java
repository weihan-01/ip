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
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printBox("Hello! I'm Alpaca", "What can I do for you?");

        while (true) {
            if (!sc.hasNextLine()) break;
            String input = sc.nextLine().trim();

            if ("bye".equals(input)) {
                printBox("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(input)) {
                if (tasks.isEmpty()) {
                    printBox("No tasks added yet.");
                } else {
                    String[] output = new String[tasks.size() + 1];
                    output[0] = "Here are the tasks in your list:";
                    for (int i = 0; i < tasks.size(); i++) {
                        output[i + 1] = (i + 1) + "." + tasks.get(i).toString();
                    }
                    printBox(output);
                }
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.substring(5).trim()) - 1;
                    Task t = tasks.get(index);
                    t.markAsDone();
                    printBox("Nice! I've marked this task as done:", "  " + t.toString());
                } catch (Exception e) {
                    printBox("Invalid index for mark command.");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    Task t = tasks.get(index);
                    t.unmark();
                    printBox("OK, I've marked this task as not done yet:", "  " + t.toString());
                } catch (Exception e) {
                    printBox("Invalid index for unmark command.");
                }
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5).trim();
                Task t = new Todo(desc);
                tasks.add(t);
                printBox("Got it. I've added this task:", "  " + t.toString(),
                         "Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                try {
                    String[] parts = input.substring(9).split(" /by ");
                    Task t = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(t);
                    printBox("Got it. I've added this task:", "  " + t.toString(),
                             "Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    printBox("Invalid deadline format. Use: deadline <desc> /by <time>");
                }
            } else if (input.startsWith("event ")) {
                try {
                    String[] parts1 = input.substring(6).split(" /from ");
                    String desc = parts1[0].trim();
                    String[] parts2 = parts1[1].split(" /to ");
                    Task t = new Event(desc, parts2[0].trim(), parts2[1].trim());
                    tasks.add(t);
                    printBox("Got it. I've added this task:", "  " + t.toString(),
                             "Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    printBox("Invalid event format. Use: event <desc> /from <start> /to <end>");
                }
            } else {
                printBox("Unknown command. Try todo, deadline, event, list, mark, unmark, bye.");
            }
        }
        sc.close();
    }
}

import java.util.Scanner;

public class Alpaca {

    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        printLine();
        System.out.println("Hello! I'm Alpaca 🦙");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            input = sc.nextLine().trim();
            if (input.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon! 🌈");
                printLine();
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                handleMark(input);
            } else if (input.startsWith("unmark ")) {
                handleUnmark(input);
            } else if (input.startsWith("todo ")) {
                handleTodo(input);
            } else if (input.startsWith("deadline ")) {
                handleDeadline(input);
            } else if (input.startsWith("event ")) {
                handleEvent(input);
            } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                handleEmptyCommand(input);
            } else {
                printLine();
                System.out.println("Huh? I don't speak gibberish! Try something I actually understand 😜");
                printLine();
            }
        }

        sc.close();
    }

    // Print line separator
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    // Task listing
    private static void listTasks() {
        printLine();
        if (taskCount == 0) {
            System.out.println("No tasks yet. Maybe add one? 📝");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        printLine();
    }

    // Handle Todo
    private static void handleTodo(String input) {
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) {
            printLine();
            System.out.println("Whoa there! You need to actually tell me what to do. I can't read minds 🦙");
            printLine();
            return;
        }
        Task t = new Todo(desc);
        tasks[taskCount++] = t;
        printAddedTask(t);
    }

    // Handle Deadline
    private static void handleDeadline(String input) {
        String[] parts = input.substring(9).split("/by");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            printLine();
            System.out.println("Oops! Deadlines need a name, not magic! 🪄");
            printLine();
            return;
        }
        Task t = new Deadline(parts[0].trim(), parts[1].trim());
        tasks[taskCount++] = t;
        printAddedTask(t);
    }

    // Handle Event
    private static void handleEvent(String input) {
        String[] parts = input.substring(6).split("/from");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            printLine();
            System.out.println("Hold up! Events need a name. I’m not a fortune teller! 🔮");
            printLine();
            return;
        }
        String name = parts[0].trim();
        String[] times = parts[1].split("/to");
        if (times.length < 2) {
            printLine();
            System.out.println("Oops! Events need both a start and end time! ⏰");
            printLine();
            return;
        }
        Task t = new Event(name, times[0].trim(), times[1].trim());
        tasks[taskCount++] = t;
        printAddedTask(t);
    }

    private static void printAddedTask(Task t) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    // Handle mark/unmark
    private static void handleMark(String input) {
        try {
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            tasks[index].markAsDone();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[index]);
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("Oops! That task number doesn't exist. 🐫");
            printLine();
        }
    }

    private static void handleUnmark(String input) {
        try {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            tasks[index].markAsNotDone();
            printLine();
            System.out.println("OK! I've marked this task as not done yet:");
            System.out.println("  " + tasks[index]);
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("Oops! That task number doesn't exist. 🐫");
            printLine();
        }
    }

    private static void handleEmptyCommand(String input) {
        printLine();
        switch (input) {
            case "todo":
                System.out.println("Whoa there! You need to actually tell me what to do. I can't read minds 🦙");
                break;
            case "deadline":
                System.out.println("Oops! Deadlines need a name, not magic! 🪄");
                break;
            case "event":
                System.out.println("Hold up! Events need a name. I’m not a fortune teller! 🔮");
                break;
        }
        printLine();
    }
}
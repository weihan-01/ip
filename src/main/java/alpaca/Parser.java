package alpaca;

import alpaca.command.*;

public class Parser {
    public static Command parse(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String args = words.length > 1 ? words[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(args, true);
            case "unmark":
                return new MarkCommand(args, false);
            case "delete":
                return new DeleteCommand(args);
            case "todo":
                return new AddCommand("todo", args);
            case "deadline":
                return new AddCommand("deadline", args);
            case "event":
                return new AddCommand("event", args);
            case "find":
                return new FindCommand(args);
            default:
                return new UnknownCommand();
        }
    }
}
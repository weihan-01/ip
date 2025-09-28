package alpaca.command;

import alpaca.Storage;
import alpaca.task.Task;
import alpaca.TaskList;
import alpaca.Ui;

/**
 * Finds and lists all tasks containing the given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();

        ui.showLine();
        response.append("Here are the matching tasks in your list:\n");
        System.out.println("Here are the matching tasks in your list:");

        int count = 1;
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                String line = count + "." + t;
                response.append(line).append("\n");
                System.out.println(line);
                count++;
            }
        }

        if (count == 1) {
            response.append("No matching tasks found\n");
            System.out.println("No matching tasks found");
        }

        ui.showLine();
        return response.toString().trim();
    }
}

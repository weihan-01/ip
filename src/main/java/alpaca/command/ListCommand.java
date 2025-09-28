package alpaca.command;

import alpaca.Storage;
import alpaca.TaskList;
import alpaca.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();

        ui.showLine();

        if (tasks.isEmpty()) {
            String msg = "No tasks yet. Maybe add one?";
            response.append(msg).append("\n");
            System.out.println(msg);
        } else {
            String header = "Here are the tasks in your list:";
            response.append(header).append("\n");
            System.out.println(header);

            for (int i = 0; i < tasks.size(); i++) {
                String line = (i + 1) + "." + tasks.get(i);
                response.append(line).append("\n");
                System.out.println(line);
            }
        }

        ui.showLine();
        return response.toString().trim();
    }
}

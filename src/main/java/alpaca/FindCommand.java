package alpaca;

/**
 * Finds and lists all tasks containing the given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                System.out.println(count + "." + t);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No matching tasks found.");
        }
        ui.showLine();
    }
}
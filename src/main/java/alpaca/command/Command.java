package alpaca.command;

import alpaca.Storage;
import alpaca.TaskList;
import alpaca.Ui;

/**
 * Abstract base class for all user commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks The task list.
     * @param ui The UI handler.
     * @param storage The storage handler.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if this command should exit the program.
     * @return true if exit, false otherwise.
     */
    public boolean isExit() { return false; }
}
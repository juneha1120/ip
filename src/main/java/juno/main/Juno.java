package juno.main;

import juno.commands.Command;
import juno.commands.ExitCommand;
import juno.exceptions.JunoException;
import juno.exceptions.JunoTestException;
import juno.parser.Parser;
import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

public class Juno {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Juno(String filePath) {
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        new Juno("./data/juno.txt").run();
    }

    public void run() {
        this.ui.showGreetingMessage();
        try {
            this.tasks = this.storage.loadTasks();
            ui.showLoadingMessage(this.tasks);
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = this.parser.parse(fullCommand);
                this.ui.showDivider();
                c.execute(this.tasks, this.storage, this.ui);
                isExit = ExitCommand.isExit(c);
                this.ui.showDivider();
            } catch (JunoTestException ignored) {
                // Ignored
            } catch (JunoException e) {
                this.ui.showError(e.getMessage());
                this.ui.showDivider();
            }
        }
    }
}

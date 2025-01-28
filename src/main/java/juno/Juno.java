package juno;

import command.Command;
import command.ExitCommand;

import exceptions.JunoException;
import exceptions.JunoTestException;

import parser.Parser;

import task.TaskList;

import storage.Storage;

import ui.Ui;

public class Juno {
    private final Parser parser;
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Juno(String filePath) {
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.ui = new Ui();
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
            } catch (JunoException e) {
                this.ui.showError(e.getMessage());
                this.ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Juno("./data/juno.txt").run();
    }
}
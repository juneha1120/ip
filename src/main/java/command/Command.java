package command;

import exceptions.JunoException;

import task.TaskList;

import storage.Storage;

import ui.Ui;

public abstract class Command {
    public static final String EXAMPLE_PREFIX = " ".repeat(7) + "For example, ";
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException;
}

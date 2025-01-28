package juno.command;

import juno.exceptions.JunoException;

import juno.task.TaskList;

import juno.storage.Storage;

import juno.ui.Ui;

public abstract class Command {
    public static final String EXAMPLE_PREFIX = " ".repeat(7) + "For example, ";
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException;
}

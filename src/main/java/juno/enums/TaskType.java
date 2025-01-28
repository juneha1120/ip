package juno.enums;

/**
 * Represents the different types of tasks in the system.
 * <p>
 * The {@code TaskType} enum defines three types of tasks:
 * <ul>
 *     <li>{@link #TODO} - A task without a date or time associated with it.</li>
 *     <li>{@link #DEADLINE} - A task with a specific due date and time.</li>
 *     <li>{@link #EVENT} - A task that has a start and end date and time.</li>
 * </ul>
 */
public enum TaskType {
    TODO, DEADLINE, EVENT
}

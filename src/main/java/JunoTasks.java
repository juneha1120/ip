import java.util.*;
import enums.JunoType;
import static enums.JunoType.*;

public class JunoTasks {
    private Map<Integer, JunoTask> taskList;
    private int taskNum;

    public JunoTasks() {
        this.taskList = new LinkedHashMap<>();
        this.taskNum = 0;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    public void addTask(JunoTask task) {
        this.taskList.put(++this.taskNum, task);
    }

    public JunoTask makeTask(JunoType type, String description) throws JunoException {
        JunoTask curr;
        if (type.equals(TODO)) {
            if (description.isEmpty()) throw new JunoException(TODO, true, true);
            curr = new JunoTodo(description);
        } else if (type.equals(DEADLINE)) {
            if (description.isEmpty()) throw new JunoException(DEADLINE, false, true);
            else if (!description.contains("/by")) throw new JunoException(DEADLINE, true, true);
            String[] desc = description.split("/by", 2);
            curr = new JunoDeadline(desc[0].trim(), desc[1].trim());
        } else {
            if (description.isEmpty()) throw new JunoException(EVENT, false, true);
            else if (!description.contains("/from") || !description.contains("/to") ) throw new JunoException(EVENT, true, true);
            String[] desc = description.split("/from", 2);
            String[] fromTo = desc[1].split("/to", 2);
            curr = new JunoEvent(desc[0].trim(), fromTo[0].trim(), fromTo[1].trim());
        }
        return curr;
    }

    public JunoTask deleteTask(String command) throws JunoException {
        JunoTask curr;
        int currNum;
        try { currNum = Integer.parseInt(command); }
        catch (NumberFormatException e) { throw new JunoException(DELETE, true, true); }
        if (this.taskList.containsKey(currNum)) {
            curr = this.taskList.remove(currNum);
            this.renumberTasks();
        } else {
            throw new JunoException(DELETE, true, true);
        }
        return curr;
    }

    public void renumberTasks() {
        Map<Integer, JunoTask> newTaskList = new LinkedHashMap<>();
        int newTaskNum = 0;

        for (Map.Entry<Integer, JunoTask> entry : this.taskList.entrySet()) {
            newTaskList.put(++newTaskNum, entry.getValue());
        }

        this.taskList = newTaskList;
        this.taskNum = newTaskNum;
    }

    public JunoTask markTask(String command) throws JunoException {
        int currNum;
        try {
            currNum = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new JunoException(MARK, true, false);
        }

        JunoTask curr = this.taskList.get(currNum);
        if (curr == null) throw new JunoException(MARK, true, false);
        else if (curr.isDone()) throw new JunoException(MARK, true, true);

        curr.mark();
        return curr;
    }

    public JunoTask unmarkTask(String command) throws JunoException {
        int currNum;
        try {
            currNum = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new JunoException(UNMARK, true, true);
        }

        JunoTask curr = this.taskList.get(currNum);
        if (curr == null) throw new JunoException(UNMARK, true, true);
        else if (!curr.isDone()) throw new JunoException(UNMARK, true, false);

        curr.unmark();
        return curr;
    }

    public void showTasks() throws JunoException {
        if (this.taskNum == 0) throw new JunoException(LIST, true, true);
        System.out.println(" Here's what you have :");
        this.taskList.forEach((taskNum, task) ->
                                System.out.println("  " + taskNum + ". " + task.toString()));
    }
}
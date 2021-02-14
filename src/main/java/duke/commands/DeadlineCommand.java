package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.tasks.TaskList;
import duke.tasks.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String taskDescription;
    private LocalDate dueDate;

    public DeadlineCommand(TaskList taskList, String taskDescription, LocalDate dueDate) {
        super(taskList);
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
    }

    public DeadlineCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.taskDescription = "";
        this.dueDate = null;
    }

    public Command process() {
        try {
            if (this.getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of a deadline cannot be empty.");
            }
            String taskDescription = this.getDescription(this.getUserInput(), "\n");
            LocalDate dueDate = this.getDueDate(this.getUserInput(), "/by");
            return new DeadlineCommand(this.getTaskList(), taskDescription, dueDate);
        } catch (InsufficientArgumentsException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    public TaskList execute() {
        LocalDate dueDate = this.dueDate;
        String taskDescription = this.taskDescription;
        Deadline deadline = new Deadline(taskDescription, dueDate);
        TaskList taskList = this.getTaskList();
        int initialSize = taskList.size();
        taskList = taskList.add(deadline);
        assert(initialSize + 1 == taskList.size()); // ensure that event is properly added
        return taskList;
    }

    public String toString() {
        String message = "Got it. I've added this task:\n";
        Deadline deadline = new Deadline(this.taskDescription, this.dueDate);
        message += deadline.toString() + "\n";
        message += "Now you have " + (this.getTaskList().size() + 1) + " tasks in the list.\n";
        return message;
    }
}
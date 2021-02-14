package duke.tasks;

import duke.parser.DuplicateException;

import java.util.ArrayList;

/**
 * TaskList stores a list of Task Objects
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList add(Task task) {
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (Task t: this.tasks) {
            newTaskList.add(t);
        }
        newTaskList.add(task);
        assert newTaskList.size() >= 1;
        return new TaskList(newTaskList);
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public TaskList remove(int index) {
        ArrayList<Task> newTaskList = this.tasks;
        newTaskList.remove(index);
        return new TaskList(newTaskList);
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public void hasDuplicate(String taskDescription) throws DuplicateException {
        for (Task t: this.tasks) {
            if (t.getDescription().equals(taskDescription)) {
                throw new DuplicateException("This task has already been recorded!\n"
                        + "Please enter another task!");
            }
        }
    }

    public String toString() {
        String res = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task t : this.tasks) {
            res += "     " + count + "." + t;
            count++;
        }
        return res;
    }
}

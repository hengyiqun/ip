package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.commands.Command;

import java.io.FileNotFoundException;

/**
 * Duke allows the user to maintain a list of tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.ui.greet();
        this.storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(this.tasks);
        Command command = parser.parse(input);
        command = command.process();
        // message is generated before modifying the task list so that we can get
        // the corresponding string for the deleted task when delete is called
        String message = command.toString();
        this.tasks = command.execute();
        this.storage.save(this.tasks);
        return "Duke says:\n" + this.ui.format(message);
    }

    public String getGreeting() {
        return this.ui.greet();
    }
}
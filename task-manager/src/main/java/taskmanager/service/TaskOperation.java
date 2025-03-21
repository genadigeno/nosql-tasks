package taskmanager.service;

import lombok.AllArgsConstructor;
import taskmanager.core.TaskManager;
import taskmanager.operations.Command;

@AllArgsConstructor
public class TaskOperation {
    private final TaskManager taskManager;
    private final Parser parser;

    public void performRequestedOperations(String input) {
        Command command = parser.parse(input);
        command.execute(taskManager);
    }
}

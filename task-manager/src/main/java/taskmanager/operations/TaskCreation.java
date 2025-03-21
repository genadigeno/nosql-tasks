package taskmanager.operations;

import lombok.AllArgsConstructor;
import taskmanager.core.TaskManager;
import taskmanager.model.Task;

@AllArgsConstructor
public class TaskCreation implements Command {
    private final Task task;

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.create(task);
    }
}

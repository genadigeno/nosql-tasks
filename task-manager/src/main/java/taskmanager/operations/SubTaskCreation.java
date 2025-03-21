package taskmanager.operations;

import taskmanager.core.TaskManager;
import taskmanager.model.Task;

import java.util.Map;

public class SubTaskCreation implements Command{
    private final Task task;

    public SubTaskCreation(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.createSubtask(task);
    }
}

package taskmanager.operations;

import taskmanager.core.TaskManager;

public class TaskListOut implements Command {
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.getAll();
    }
}

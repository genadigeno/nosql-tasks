package taskmanager.operations;

import taskmanager.core.TaskManager;

public class OverDueTaskListOut implements Command {
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.getAllOverDue();
    }
}

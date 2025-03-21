package taskmanager.operations;

import taskmanager.core.TaskManager;

public interface Command {
    void execute(TaskManager taskManager);
}

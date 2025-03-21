package taskmanager.operations;

import taskmanager.core.TaskManager;

import java.util.Map;

public class TaskDeletion implements Command {
    private final Map<String, Object> queryMap;
    public TaskDeletion(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.delete(queryMap);
    }
}

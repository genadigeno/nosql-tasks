package taskmanager.operations;

import taskmanager.core.TaskManager;

import java.util.Map;

public class SubTaskDeletion implements Command {
    private final Map<String, Object> queryMap;

    public SubTaskDeletion(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.deleteSubTask(queryMap);
    }
}

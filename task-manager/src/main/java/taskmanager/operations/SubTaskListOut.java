package taskmanager.operations;

import taskmanager.core.TaskManager;

import java.util.Map;

public class SubTaskListOut implements Command {
    private final Map<String, Object> queryMap;

    public SubTaskListOut(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.getSubtasksForQueriedTasks(queryMap);
    }
}

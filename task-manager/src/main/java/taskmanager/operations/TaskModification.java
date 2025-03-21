package taskmanager.operations;

import taskmanager.core.TaskManager;
import taskmanager.model.Task;

import java.util.Map;

public class TaskModification implements Command {
    private final Map<String, Object> fieldMap;
    private final Map<String, Object> queryMap;

    public TaskModification(Map<String, Object> fieldMap, Map<String, Object> queryMap) {
        this.fieldMap = fieldMap;
        this.queryMap = queryMap;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.update(fieldMap, queryMap);
    }
}

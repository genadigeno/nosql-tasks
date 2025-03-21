package taskmanager.operations;

import taskmanager.core.TaskManager;

import java.util.Map;

public class SubTaskModification implements Command {
    private final Map<String, Object> fieldMap;
    private final Map<String, Object> queryMap;

    public SubTaskModification(Map<String, Object> fieldMap, Map<String, Object> queryMap) {
        this.fieldMap = fieldMap;
        this.queryMap = queryMap;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.updateSubTask(fieldMap, queryMap);
    }
}

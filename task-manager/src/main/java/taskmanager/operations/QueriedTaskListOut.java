package taskmanager.operations;

import taskmanager.core.TaskManager;

import java.util.Map;

public class QueriedTaskListOut implements Command {
    private final Map<String, Object> queryMap;
    public QueriedTaskListOut(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    @Override
    public void execute(TaskManager taskManager) {
        taskManager.query(queryMap);
    }
}

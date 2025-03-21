package taskmanager.core;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import dev.morphia.Datastore;
import dev.morphia.DeleteOptions;
import dev.morphia.UpdateOptions;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.filters.Filter;
import dev.morphia.query.internal.MorphiaCursor;
import dev.morphia.query.updates.UpdateOperator;
import dev.morphia.query.updates.UpdateOperators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import taskmanager.model.Task;

import java.time.LocalDateTime;
import java.util.*;

import static dev.morphia.query.filters.Filters.*;

@Slf4j
@AllArgsConstructor
public class TaskManager {

    private final Datastore datastore;

    public void create(Task task) {
        datastore.save(task);
    }

    public void delete(Map<String, Object> queryMap) {
        Query<Task> taskQuery = getQuery(queryMap);
        DeleteResult deleteResult = taskQuery.delete(new DeleteOptions().multi(true));
        log.info("Deleted {} tasks", deleteResult.getDeletedCount());
    }

    public void update(Task task) {
        datastore.save(task);
    }

    public void update(Map<String, Object> fieldMap, Map<String, Object> queryMap) {
        Query<Task> taskQuery = getQuery(queryMap);

        List<UpdateOperator> updateOperators = new ArrayList<>();
        fieldMap.forEach((key, value) -> {
            updateOperators.add(UpdateOperators.set(key, value));
        });

        taskQuery.update(updateOperators)
                .execute();

        log.info("Updated tasks");
    }

    public void get(ObjectId id) {
        Query<Task> taskQuery = datastore.createQuery(Task.class)
                .filter(eq("_id", id));

        log.info("total tasks: " + taskQuery.count());

        taskQuery.forEach(task -> {
            log.info(task.toString());
        });
    }

    public void getAll() {
        Query<Task> taskQuery = datastore.find(Task.class);
        log.info("total tasks: " + taskQuery.count());
        taskQuery.forEach(task -> {
            log.info(task.toString());
        });
    }

    public void getAllOverDue() {
        Query<Task> overdueTaskQuery = datastore.find(Task.class)
                .filter("deadline <", LocalDateTime.now());

        log.info("Total overdue tasks: " + overdueTaskQuery.count());
        overdueTaskQuery.forEach(task -> log.info(task.toString()));
    }

    public void query(Map<String, Object> queryMap) {
        Query<Task> taskQuery = getQuery(queryMap);
        log.info("total tasks: " + taskQuery.count());
        taskQuery.forEach(task -> {
            log.info(task.toString());
        });
    }

    public void getSubtasksForQueriedTasks(Map<String, Object> queryMap) {
        Query<Task> taskQuery = getQuery(queryMap);
        log.info("Total matched tasks: " + taskQuery.count());

        taskQuery.forEach(task -> {
            log.info("Task: " + task.getName());
            if (task.getSubtasks() != null && !task.getSubtasks().isEmpty()) {
                task.getSubtasks().forEach(subtask -> log.info("  Subtask: " + subtask.toString()));
            } else {
                log.info("  No subtasks.");
            }
        });
    }

    private Query<Task> getQuery(Map<String, Object> queryMap) {
        Set<String> keySet = queryMap.keySet();
        Query<Task> taskQuery = datastore.find(Task.class);
        List<Filter> filters = new ArrayList<>();
        for (String key : keySet) {
            filters.add(eq(key, queryMap.get(key)));
        }
        taskQuery.filter(filters.toArray(new Filter[0]));
        return taskQuery;
    }

    public void createSubtask(Task task) {
        //
    }

    public void updateSubTask(Map<String, Object> fieldMap, Map<String, Object> queryMap) {
        //
    }

    public void deleteSubTask(Map<String, Object> queryMap) {
        //
    }
}

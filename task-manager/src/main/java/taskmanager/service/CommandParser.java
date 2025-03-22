package taskmanager.service;

import taskmanager.model.Category;
import taskmanager.model.SubTask;
import taskmanager.model.Task;
import taskmanager.operations.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandParser implements Parser {
    private static final Pattern QUERY_PATTERN = Pattern.compile("(\\w+)=\"([^\"]*)\"|(\\w+)=([^\\s]+)");

    @Override
    public Command parse(String input) {
        if (!input.trim().startsWith("tm")) {
            throw new IllegalArgumentException("Invalid operation: " + input);
        }
        input = input.replaceFirst("tm", "").trim();

        // 1. List tasks
        if (input.startsWith("list")) {
            input = input.replaceFirst("list", "").trim();

            // Overdue tasks
            if (input.startsWith("--overdue")) {
                return new OverDueTaskListOut();
            }
            // Filtered tasks
            else if (input.startsWith("--query")) {
                input = input.replaceFirst("--query", "").trim();
                return new QueriedTaskListOut(buildQueryMap(input));
            }
            // Subtask listing
            else if (input.startsWith("--subtask")) {
                input = input.replaceFirst("--subtask", "").trim();
                Map<String, Object> queryMap = input.startsWith("--query")
                        ? buildQueryMap(input.replaceFirst("--query", "").trim())
                        : Map.of();
                return new SubTaskListOut(queryMap);
            }
            // Default: return all tasks
            return new TaskListOut();
        }

        // 2. Create a task
        else if (input.startsWith("create")) {
            input = input.replaceFirst("create", "").trim();
            if (input.startsWith("--subtask")) {
                input = input.replaceFirst("--subtask", "").trim();
                Map<String, Object> fieldMap = input.startsWith("--body")
                        ? buildQueryMap(input.replaceFirst("--body", "").trim())
                        : Map.of();

                Task task = createTaskFromFields(fieldMap);
                return new SubTaskCreation(task);
            } else {
                Map<String, Object> fieldMap = input.startsWith("--body")
                        ? buildQueryMap(input.replaceFirst("--body", "").trim())
                        : Map.of();
                Task task = createTaskFromFields(fieldMap);
                return new TaskCreation(task);
            }
        }

        // 3. Update a task
        else if (input.startsWith("update")) {
            input = input.replaceFirst("update", "").trim();
            if (input.startsWith("--subtask")) {
                input = input.replaceFirst("--subtask", "").trim();

                String query = input.replaceFirst("--query", "").trim();
                query = query.substring(query.indexOf("--query") + 1, query.indexOf("--body"));
                Map<String, Object> queryMap = buildQueryMap(query);

                String fields = input.substring(input.indexOf("--body") + 1);
                Map<String, Object> fieldMap = buildQueryMap(fields);

                return new SubTaskModification(fieldMap, queryMap);
            } else {

                String query = input.replaceFirst("--query", "").trim();
                query = query.substring(query.indexOf("--query") + 1, query.indexOf("--body"));
                Map<String, Object> queryMap = buildQueryMap(query);

                String fields = input.substring(input.indexOf("--body") + "--body".length()+1);
                Map<String, Object> fieldMap = buildQueryMap(fields);

                return new TaskModification(fieldMap, queryMap);
            }
        }

        // 4. Delete a task
        else if (input.startsWith("delete")) {
            input = input.replaceFirst("delete", "").trim();
            if (input.startsWith("--subtask")) {
                input = input.replaceFirst("--subtask", "").trim();
                Map<String, Object> queryMap = input.startsWith("--query")
                        ? buildQueryMap(input.replaceFirst("--query", "").trim())
                        : Map.of();

                return new SubTaskDeletion(queryMap);
            } else {
                input = input.replaceFirst("--query", "").trim();
                return new TaskDeletion(buildQueryMap(input));
            }
        }

        throw new IllegalArgumentException("Invalid operation: " + input);
    }

    /**
     * Parses query parameters and returns a Map<String, Object>.
     * Supports quoted values like name="dummy name".
     */
    private Map<String, Object> buildQueryMap(String query) {
        Map<String, Object> queryMap = new HashMap<>();
        Matcher matcher = QUERY_PATTERN.matcher(query);

        while (matcher.find()) {
            String key = matcher.group(1) != null ? matcher.group(1) : matcher.group(3);
            String value = matcher.group(2) != null ? matcher.group(2) : matcher.group(4);
            queryMap.put(key, value);
        }

        /*if (queryMap.isEmpty()) {
            throw new IllegalArgumentException("Invalid query parameters.");
        }*/
        return queryMap;
    }

    private Task createTaskFromFields(Map<String, Object> fieldMap) {
        Task task = new Task();
        task.setCreated(LocalDateTime.now());
        task.setDeadline(LocalDateTime.now().plusDays(1));

        if (fieldMap.containsKey("name")) {
            task.setName((String) fieldMap.get("name"));
        }
        if (fieldMap.containsKey("description")) {
            task.setDescription((String) fieldMap.get("description"));
        }
        if (fieldMap.containsKey("category")) {
            task.setCategory(Category.valueOf(((String) fieldMap.get("category")).toUpperCase()));
        }
        /*if (fieldMap.containsKey("subtasks")) {
            task.setSubtasks(parseSubtasks((String) fieldMap.get("subtasks")));
        }*/

        return task;
    }

    private List<SubTask> parseSubtasks(Map<String, String> subtasksMap) {
        if (subtasksMap == null || subtasksMap.isEmpty()) {
            throw new IllegalArgumentException("Subtasks map cannot be null or empty");
        }

        return subtasksMap.entrySet().stream()
                .map(entry -> new SubTask(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}

package taskmanager.service;

import taskmanager.model.Category;
import taskmanager.model.SubTask;
import taskmanager.model.Task;
import taskmanager.operations.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class InputParser implements Parser {
    private static final Pattern QUERY_PATTERN = Pattern.compile("(\\w+)=\"([^\"]*)\"|(\\w+)=([^\\s]+)");

    @Override
    public Command parse(String input) {
        if (input.trim().startsWith("tm")) {
            input = input.replace("tm", "");

            //1. list tasks
            if (input.trim().startsWith("list")) {
                input = input.replace("list", "");
                if (input.trim().startsWith("--overdue")) {
                    return new OverDueTaskListOut();
                }
                else if (input.trim().startsWith("--query")) {
                    input = input.replace("--query", "").trim();
                    Map<String, Object> queryMap = buildQueryMap(input);
                    return new QueriedTaskListOut(queryMap);
                }
                //find tasks
                else if (input.trim().startsWith("--subtask")) {
                    input = input.replace("--subtask", "").trim();
                    if (!input.trim().startsWith("--query")) {
                        return new SubTaskListOut(Map.of());
                    }
                    input = input.replace("--query", "").trim();
                    String[] queries = input.split("");
                    //validateQuery(queries);
                    Map<String, Object> queryMap = buildQueryMap(input);
                    return new SubTaskListOut(queryMap);
                }

                return new TaskListOut();
            }
            //create a task
            else if (input.trim().startsWith("create")) {
                input = input.replace("create", "");
                if (input.trim().startsWith("--subtask")) {
                    //TODO
                } else{
                    Task task = new Task();
                    //TODO
                /*task.setName("dummy name");
                task.setDescription("dummy description");
                task.setDeadline(LocalDateTime.now().plusDays(1));
                task.setCreated(LocalDateTime.now());
                task.setCategory(Category.CRITICAL);
                task.setSubtasks(List.of(
                        new SubTask("to do", "not done"),
                        new SubTask("future task", "will b done"),
                        new SubTask("send email","sending")
                ));*/
                    return new TaskCreation(task);
                }
            }
            //update tasks
            else if (input.trim().startsWith("update")) {
                input = input.replace("update", "");
                if (input.trim().startsWith("--subtask")) {
                    //TODO
                } else{
                    //TODO
                }
            }
            //update tasks
            else if (input.trim().startsWith("delete")) {
                input = input.replace("delete", "");
                if (input.trim().startsWith("--subtask")) {
                    //TODO
                } else{
                    //TODO
                }
            }
        }
        throw new IllegalArgumentException("invalid operation: " + input);
    }

    private static Map<String, Object> buildQueryMap(String input) {
        Map<String, Object> queryMap = new HashMap<>();
        Matcher matcher = QUERY_PATTERN.matcher(input);

        while (matcher.find()) {
            String key   = matcher.group(1) != null ? matcher.group(1) : matcher.group(3);
            String value = matcher.group(2) != null ? matcher.group(2) : matcher.group(4);
            queryMap.put(key, value);
        }

        if (queryMap.isEmpty()) {
            throw new IllegalArgumentException("Invalid query: " + input);
        }
        return queryMap;
    }

    //must be key=value list
   /* private static void validateQuery(String[] queries) {
        for (String query : queries) {
            int equalIndex = query.indexOf("="); // Find first occurrence of '='

            if (equalIndex == -1 || equalIndex == query.length() - 1) {
                throw new IllegalArgumentException("invalid query: " + query);
            }

            String key = query.substring(0, equalIndex).trim();
            String value = query.substring(equalIndex + 1).trim();

            if (key.isEmpty() || value.isEmpty()) {
                throw new IllegalArgumentException("invalid query: " + query);
            }
        }
    }*/
}

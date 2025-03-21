package taskmanager;

import lombok.extern.slf4j.Slf4j;
import taskmanager.core.MongoDBConnection;
import taskmanager.core.TaskManager;
import taskmanager.service.CommandParser;
import taskmanager.service.InputParser;
import taskmanager.service.Parser;
import taskmanager.service.TaskOperation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

@Slf4j
public class TaskManagerApplication {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
        log.info("starting the task manager application...");

        TaskManager taskManager = new TaskManager(MongoDBConnection.getDatastore());
        Parser parser = new CommandParser();
        TaskOperation taskOperation = new TaskOperation(taskManager, parser);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (type 'exit' to quit): ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                log.info("Exiting the application...");
                break;
            }

            taskOperation.performRequestedOperations(input);
        }

        scanner.close();
        log.info("application closed.");
    }
}
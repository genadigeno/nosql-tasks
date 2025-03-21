package taskmanager.service;

import taskmanager.operations.Command;

public interface Parser {
    Command parse(String input);
}

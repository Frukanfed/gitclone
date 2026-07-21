package cli;

import commands.AddCommand;
import commands.Command;
import commands.InitCommand;
import commands.StatusCommand;
import repository.Repository;

public class CommandParser {
    public Command parse(String[] input, Repository repository) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("No command provided");
        }

        // Clean input and match to the correct command implementation
        return switch (input[0]) {
            case "init" -> new InitCommand(repository);
            case "status" -> new StatusCommand(repository);
            case "add" -> new AddCommand(repository);
            default -> throw new IllegalArgumentException("Invalid command: " + input[0]);
        };
    }
}

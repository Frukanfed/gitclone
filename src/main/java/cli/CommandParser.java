package cli;

import commands.AddCommand;
import commands.Command;
import commands.InitCommand;
import commands.StatusCommand;

public class CommandParser {
    public Command parse(String[] input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("No command provided");
        }

        // Clean input and match to the correct command implementation
        return switch (input[0]) {
            case "init" -> new InitCommand();
            case "status" -> new StatusCommand();
            case "add" -> new AddCommand();
            default -> throw new IllegalArgumentException("Invalid command: " + input[0]);
        };
    }
}

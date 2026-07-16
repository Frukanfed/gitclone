package cli;

import commands.Command;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No command provided");
            System.exit(1);
        }

        String command = args[0];
        if (!command.equals("init") && !command.equals("status")) {
            System.err.println("Invalid command");
            System.exit(1);
        }

        CommandParser parser = new CommandParser();

        Command cmd = parser.parse(command);
        cmd.execute();
    }
}

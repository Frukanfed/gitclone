package cli;

import commands.Command;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No command provided");
            System.exit(1);
        }

        CommandParser parser = new CommandParser();

        try {
            Command cmd = parser.parse(args[0]);
            cmd.execute();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

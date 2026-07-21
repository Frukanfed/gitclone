package cli;

import commands.Command;
import repository.Repository;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No command provided");
            System.exit(1);
        }

        CommandParser parser = new CommandParser();

        try {
            Repository repository = new Repository();
            Command cmd = parser.parse(args, repository);

            String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
            cmd.execute(commandArgs);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

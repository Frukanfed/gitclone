package commands;

import repository.Repository;

public class AddCommand implements Command {

    @Override
    public void execute(String... args) {
        if (args.length < 2) {
            System.err.println("No file specified");

            return;
        }

        Repository repo = new Repository();
        repo.add(args[1]);
    }
}
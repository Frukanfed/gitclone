package commands;

import repository.Repository;

public class AddCommand implements Command {
    private final Repository repository;

    public AddCommand(Repository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(String... args) {
        if (args.length < 1) {
            System.err.println("No file specified");

            return;
        }

        repository.add(args[0]);
    }
}
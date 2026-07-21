package commands;

import repository.Repository;

public class StatusCommand implements Command {
    private final Repository repository;

    public StatusCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String... args) {
        System.out.println("Repository status");
    }
}

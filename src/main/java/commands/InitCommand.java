package commands;

import repository.Repository;

public class InitCommand implements Command {
    private final Repository repository;

    public InitCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String... args) {
        repository.init();
    }
}

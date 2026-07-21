package commands;

import repository.Repository;

public class InitCommand implements Command {
    @Override
    public void execute(String... args) {
        Repository repo = new Repository();

        repo.init();
    }
}

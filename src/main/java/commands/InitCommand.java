package commands;

import repository.Repository;

public class InitCommand implements Command {
    public void execute() {
        Repository repo = new Repository();

        repo.init();
    }
}

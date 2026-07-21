package commands;

public class StatusCommand implements Command {
    @Override
    public void execute(String... args) {
        System.out.println("Repository status");
    }
}

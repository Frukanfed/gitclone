package commands;

public class StatusCommand implements Command {
    public void execute() {
        System.out.println("Repository status");
    }
}

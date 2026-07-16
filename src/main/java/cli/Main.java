package cli;

import commands.InitCommand;
import commands.StatusCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        commandLoop:
        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            switch (input) {
                case "init":
                    new InitCommand().execute();
                    break;
                case "status":
                    new StatusCommand().execute();
                    break;
                case "q":
                case "quit":
                    System.out.println("Exiting program...");
                    break commandLoop;
                default:
                    System.out.println("Invalid command: " + input);
                    break;
            }
        }

    }
}
package cli;

import commands.InitCommand;
import commands.StatusCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No command provided");
            System.exit(1);
        }

        String command = args[0];
        if (!command.equals("init") && !command.equals("status")) {
            System.err.println("Invalid command");
            System.exit(1);
        }

        switch (command) {
            case "init":
                new InitCommand().execute();
                break;
            case "status":
                new StatusCommand().execute();
                break;
            default:
                System.err.println("Invalid command");
                break;
        }
    }
}

//        Scanner scan = new Scanner(System.in);
//
//        commandLoop:
//        while (scan.hasNextLine()) {
//            String input = scan.nextLine();
//
//            switch (input) {
//                case "init":
//                    new InitCommand().execute();
//                    break;
//                case "status":
//                    new StatusCommand().execute();
//                    break;
//                case "q":
//                case "quit":
//                    System.out.println("Exiting program...");
//                    break commandLoop;
//                default:
//                    System.out.println("Invalid command: " + input);
//                    break;
//            }
//        }
//
//        scan.close();
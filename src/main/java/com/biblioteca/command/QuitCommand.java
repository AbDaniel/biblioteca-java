package com.biblioteca.command;

public class QuitCommand implements Command {

    public static String EXIT_MESSAGE = "Thank you! For Using Biblioteca";

    private String welcomeMessage;

    public QuitCommand(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @Override
    public void execute() {
        System.out.println(welcomeMessage);
        System.exit(0);
    }

}

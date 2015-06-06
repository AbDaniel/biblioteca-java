package com.biblioteca.command;

public class QuitCommand implements Command {

    public static String EXIT_MESSAGE = "Thank you! For Using Biblioteca";

    @Override
    public void execute() {
        System.out.println(EXIT_MESSAGE);
    }

}

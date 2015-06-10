package com.biblioteca.command;

public class Quit implements Action {

    private String welcomeMessage;

    public Quit(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @Override
    public void execute() {
        System.out.println(welcomeMessage);
        System.exit(0);
    }

}

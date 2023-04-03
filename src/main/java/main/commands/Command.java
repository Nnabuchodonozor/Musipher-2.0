package main.commands;

import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<String> arguments;

    public Command(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    public abstract void execute();



}

package main.commands;

import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<String> arguments;
    FileManager fileManager;
    public Command(ArrayList<String> arguments, FileManager fileManager) {
        this.arguments = arguments;
        this.fileManager = fileManager;
    }

    public abstract void execute();



}

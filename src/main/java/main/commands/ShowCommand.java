package main.commands;

import java.util.ArrayList;

public class ShowCommand extends Command{


    public ShowCommand(ArrayList<String> arguments, FileManager fileManager) {
        super(arguments, fileManager);
    }

    @Override
    public void execute() {
        fileManager.print();
    }
}

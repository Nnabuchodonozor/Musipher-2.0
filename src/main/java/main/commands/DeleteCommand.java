package main.commands;

import java.io.File;
import java.util.ArrayList;

public class DeleteCommand extends Command{


    public DeleteCommand(ArrayList<String> arguments, FileManager fileManager) {
        super(arguments, fileManager);
    }

    @Override
    public void execute() {
        if(arguments.size() < 2){
            printUsage();
            return;
        }

        try {
            switch (arguments.get(1)){

                case "-t" :
                    fileManager.setText(null);
                    System.out.println("Deleted text");
                    break;
                case "-f" :
                    fileManager.setOpenFile(null);
                    System.out.println("Deleted open file");

                    break;
                case "-e" :
                    fileManager.setEncryptionFile(null);
                    System.out.println("Deleted encrypted file");

                    break;
                default:
                    printUsage();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void printUsage(){
        System.out.println("Incorrect Usage: Please follow the instructions \n" +
                "delete <-t|-f|-k> \n" +
                "-t : delete text data \n" +
                "-f : delete open file data \n" +
                "-k : delete encrytion/decription key file  \n"
                );
    }
}

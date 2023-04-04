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
                    break;
                case "-f" :
                    fileManager.setOpenFile(null);
                    break;
                case "-e" :
                    fileManager.setEncryptionFile(null);
                    break;
                case "-k":
                    fileManager.setKeyFile(null);
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
                "delete <-t|-f|-e|-k> \n" +
                "-t : delete text data \n" +
                "-f : delete open file data \n" +
                "-e : delete encrypted MIDI file \n" +
                "-k : delete encrytion/decription key file  \n"
                );
    }
}

package main.commands;

import java.io.File;
import java.util.ArrayList;

public class ImportCommand extends Command{

    public ImportCommand(ArrayList<String> arguments, FileManager fileManager) {
        super(arguments, fileManager);
    }

    @Override
    public void execute() {
        if(arguments.size() < 3){
            printUsage();
            return;
        }

        try {
        switch (arguments.get(1)){

            case "-t" :
                fileManager.setText(arguments.get(2));
                System.out.println("Imported text: " + arguments.get(2));
                break;
            case "-f" :
                File f = new File(arguments.get(2));
                fileManager.setOpenFile(f);
                System.out.println("Imported open file: " + arguments.get(2));

                break;
            case "-e" :
                File e = new File(arguments.get(2));
                fileManager.setEncryptionFile(e);
                System.out.println("Imported encrypted file: " + arguments.get(2));
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
                "import <-t|-f|-e> <filename> | <text> \n" +
                "-t : text data to be encrypted \n" +
                "-f : file data to be encrypted \n" +
                "-e : encrypted MIDI file to be decrypted \n" +
                "<text> lower case normalised base 32 text \n" +
                "<filename> path to the file");
    }
}

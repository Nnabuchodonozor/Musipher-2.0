package main.commands;

import main.utils.SemanticUtils;

import java.util.ArrayList;

public class EncryptCommand extends Command{


    public EncryptCommand(ArrayList<String> arguments, FileManager fileManager) {
        super(arguments, fileManager);
    }

    @Override
    public void execute() {

        if (arguments.size() < 3){
            printUsage();
            return;
        }

        try {
        SemanticUtils semanticUtils = new SemanticUtils();
        switch (arguments.get(1)){
            case "-t":
                if (fileManager.getText()!=null){
                    semanticUtils.encryptToMIDIFromText(fileManager.getText(), arguments.get(2));
                }else {
                    printUsage();
                }
                break;
            case "-f":
                if (fileManager.getOpenFile()!=null){
                    semanticUtils.encryptToMIDIFromFile(fileManager.getOpenFile().getAbsolutePath(),arguments.get(2),1);
                }else {
                    printUsage();
                }
                break;
            default:
                printUsage();
                break;
        }
            System.out.println("Encryption complete, stored as: music.mid");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void printUsage(){
        System.out.println("Incorrect Usage: Please follow the instructions \n" +
                "encrypt <-t|-f> <password>\n" +
                "-t : encrypt available text data \n" +
                "-f : encrypt available file data \n" +
                "<password> : Password or passphrase that will be used for hash generation \n" +
                "No whitespaces allowed, Files ought to be imported first"

        );
    }
}

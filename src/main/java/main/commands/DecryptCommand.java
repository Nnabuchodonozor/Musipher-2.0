package main.commands;

import main.utils.SemanticUtils;

import java.util.ArrayList;

public class DecryptCommand extends Command{


    public DecryptCommand(ArrayList<String> arguments, FileManager fileManager) {
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
                    if (fileManager.getEncryptionFile()!=null){
                        semanticUtils.decryptFromMIDIToText(fileManager.getEncryptionFile().getAbsolutePath(),arguments.get(2));
                    }else {
                        printUsage();
                    }
                    break;
                case "-f":
                    if (fileManager.getEncryptionFile()!=null){
                        semanticUtils.decryptFromMIDIToFile(fileManager.getEncryptionFile().getAbsolutePath(),arguments.get(2));
                        System.out.println("File decrypted, stored as: decryptedFile.<extension> ");
                    }else {
                        printUsage();
                    }
                    break;
                default:
                    printUsage();
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void printUsage(){
        System.out.println("Incorrect Usage: Please follow the instructions \n" +
                "decrypt <-t|-f> \n" +
                "-t : decrypt available text data \n" +
                "-f : decrypt available file data \n" +
                "<password> : Password or passphrase that will be used for hash generation \n" +
                "No whitespaces allowed, Files ought to be imported first"

        );
    }
}

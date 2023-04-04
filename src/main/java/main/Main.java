package main;

import main.commands.*;
import main.utils.GUI;
import org.jline.reader.*;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Musipher 2.0. The most incredible innovation in music cryptography");
//        GUI gui = new GUI();

            TerminalBuilder terminalBuilder = TerminalBuilder.builder();
            Terminal terminal = terminalBuilder.build();

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(new MyCompleter())
                    .build();
            String line;
            FileManager fileManager = new FileManager();

            while ((line = reader.readLine("> ")) != null){
//                System.out.println(line);
                ArrayList<String> splitedLine = new ArrayList<>( Arrays.asList( line.split(" ")));

            switch (splitedLine.get(0)){
                case "import":
                    ImportCommand importCommand = new ImportCommand(splitedLine,fileManager);
                    importCommand.execute();
                    break;
                case "encrypt":
                    EncryptCommand encryptCommand = new EncryptCommand(splitedLine,fileManager);
                    encryptCommand.execute();
                    break;
                case "decrypt":
                    DecryptCommand decryptCommand = new DecryptCommand(splitedLine,fileManager);
                    decryptCommand.execute();
                    break;
                case "show":
                    ShowCommand showCommand = new ShowCommand(splitedLine,fileManager);
                    showCommand.execute();
                    break;
                case "delete":
                    DeleteCommand deleteCommand = new DeleteCommand(splitedLine,fileManager);
                    deleteCommand.execute();
                    break;
                default:
                    System.out.println("Incorrect Usage: \n" +
                            "Available commands: import, delete, encrypt, decrypt, show");
                    break;
            }
                if (splitedLine.get(0).equals("quit")){
                    terminal.close();
                    break;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

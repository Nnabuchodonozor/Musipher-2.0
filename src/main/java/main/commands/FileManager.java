package main.commands;

import java.io.File;

public class FileManager {

    File openFile;
    File keyFile;
    String text;
    File encryptionFile;

    public FileManager() {
    }

    public File getEncryptionFile() {
        return encryptionFile;
    }

    public void setEncryptionFile(File encryptionFile) {
        this.encryptionFile = encryptionFile;
    }

    public File getOpenFile() {
        return openFile;
    }

    public void setOpenFile(File openFile) {
        this.openFile = openFile;
    }

    public File getKeyFile() {
        return keyFile;
    }

    public void setKeyFile(File keyFile) {
        this.keyFile = keyFile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void print(){
        if (text==null)
            System.out.println("text: NOTHING AVAILABLE");
        else
            System.out.println("text: "+ this.text);

        if (encryptionFile==null)
            System.out.println("encrypted file: NOTHING AVAILABLE");
        else
            System.out.println("encrypted file: "+ this.encryptionFile.getAbsolutePath());

        if (keyFile==null)
            System.out.println("key file: NOTHING AVAILABLE");
        else
            System.out.println("key file: "+ this.keyFile.getAbsolutePath());

        if (openFile==null)
            System.out.println("open file: NOTHING AVAILABLE");
        else
            System.out.println("open file: "+ this.openFile.getAbsolutePath());


    }
}

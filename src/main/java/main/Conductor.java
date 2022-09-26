package main;

public class Conductor {
    String strInput;
    public Conductor(String s) {
        strInput=s;
    }

    protected int getInput(int size){
        String tmp = strInput.substring(0,size);
        strInput = strInput.substring(size);
        return Integer.parseInt(tmp, 2);

    }
}

package main.composition;

public class Conductor {
    String strInput;
    public Conductor(String s) {
        strInput=s;
    }

    public int getInput(int size){
        String tmp = strInput.substring(0,size);
        strInput = strInput.substring(size);
        return Integer.parseInt(tmp, 2);

    }
}

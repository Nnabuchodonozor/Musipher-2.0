package main.composition;

import java.util.List;

public class Conductor {
    String strInput;
    String[] voices = new String[16];
    public Conductor(String s) {
        strInput=s;
    }

    public String composeSong() throws Exception {

        Key key = new Key(strInput);
        Integer[] a  = key.generateKey();
        strInput = key.getStrInput();
        Harmony harmony = new Harmony(a,strInput);
        harmony.createFunctionalHarmony(10);
        strInput = harmony.getStrInput();
        List<Integer> chordProgression = harmony.getChordProgression();
        List<Integer[]> chords = harmony.getChords();


        return null;
    }




    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }
}

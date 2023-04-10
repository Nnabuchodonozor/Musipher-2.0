package main.composition;

import java.util.List;

public class Conductor {
    String strInput;
    String[] voices = new String[16];
    public Conductor(String s) {
        strInput=s;
        for(int i = 0; i < voices.length; i++){
            voices[i] = "V"+i + " ";
        }
    }

    public String composeSong() throws Exception {

        Key key = new Key(strInput);
        Integer[] a  = key.generateKey();
        strInput = key.getStrInput();
        encodeKey(key,a);
//        Harmony harmony = new Harmony(a,strInput);
//        harmony.createFunctionalHarmony(10);
//        strInput = harmony.getStrInput();
//        List<Integer> chordProgression = harmony.getChordProgression();
//        List<Integer[]> chords = harmony.getChords();


        return connectAllVoices();
    }

    private void encodeKey( Key key,Integer[] a){
        int whatKey = key.getWhatKey();
        System.out.println("Key was: " + whatKey +" : " + a[0]);

        switch (whatKey) {
            case 0 -> this.voices[1] += a[35] + "i Ri";
            case 1 -> this.voices[1] += a[35] + "i " + a[36] + "i " + "Ri";
            case 2 -> this.voices[1] += a[35] + "i " + a[34] + "i " + "Ri";
            case 3 -> this.voices[1] += a[35] + "i " + a[35] + "i " + "Ri";
            default -> {
            }
        }
        this.voices[1] +=  " 43i";
    }

    private String connectAllVoices(){
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < voices.length; i++){
            if(i!=9)
                a.append(voices[i]).append(" ");
        }
        return  a.toString();
    }




    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }
}

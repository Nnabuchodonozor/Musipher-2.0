package main.composition;

import main.utils.MidiUtils;

import java.util.ArrayList;
import java.util.List;

public class Analyst {

    List<ArrayList<String>> parsedSong;
    String strOutput = "";
    Key key;
    Integer [] a;
    MidiUtils midiUtils;

    public Analyst(List<ArrayList<String>> parsedSong) {
        this.parsedSong = parsedSong;
    }

    public String analyzeSong(MidiUtils midiUtils) throws Exception {
        this.midiUtils = midiUtils;
        decomposeDrums();
        decomposeKey();
        decomposeHarmony();

        return strOutput;
    }

    public void decomposeKey(){
  ArrayList<String> firstMelody = parsedSong.get(2);
  firstMelody.remove(firstMelody.get(0));
        key = new Key("");
        if(firstMelody.get(1).startsWith("R")){
            int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
            a = key.generateKeyTest( choice );
            strOutput+=getChoiceString(choice,5);
            System.out.println(strOutput);
        }else {
            int a = Integer.parseInt(firstMelody.get(0),0,2,10);
            int b = Integer.parseInt(firstMelody.get(1),0,2,10);
            if(b > a){
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                this.a = key.generateKeyTest( choice + 12 );
                strOutput+=getChoiceString(choice+ 12,5);
                System.out.println(strOutput);
            }else if(a > b){
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                this.a = key.generateKeyTest( choice +24-1 );
                strOutput+=getChoiceString(choice+24-1,5);
                System.out.println(strOutput);
            }else {
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                this.a = key.generateKeyTest( choice + 29 );
                strOutput+=getChoiceString(choice+ 29,5);
                System.out.println(strOutput);
            }
        }
    }

    private void decomposeDrums(){
        Drums drums = new Drums("");
        drums.parseDrums(this.midiUtils.getUnparsedDrums());
        this.strOutput = drums.getStroutput();
    }

    private void decomposeHarmony() throws Exception{
        Harmony harmony = new Harmony(a,"");
        List<Integer> chordProgression = getChordProgression(harmony);
        harmony.decodeFunctionalHarmony(chordProgression);
        this.strOutput += harmony.getStrOutput();
    }

    private List<Integer> getChordProgression(Harmony harmony){
        List<Integer> chordProgression = new ArrayList<>();
        List<Integer[]> chords = harmony.getChords();
        ArrayList<String> flutePart = this.parsedSong.get(4);
        flutePart.remove(flutePart.get(0));

        for(int i = 0; i < 14; i++){
            for (int j = 0; j < chords.size(); j++){
                if(Integer.parseInt(flutePart.get(i).substring(0,2)) == (chords.get(j)[0] + 24))
                    chordProgression.add(j);
            }
        }
        return chordProgression;
    }

    private String getChoiceString(int choice, int length){
        String s= Integer.toBinaryString(choice);
        while (s.length()<length){
            s = "0"+s;
        }
        return s;
    }

}

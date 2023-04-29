package main.composition;

import main.utils.MidiUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
        harmony.setChordProgression(chordProgression);
        harmony.decodeFunctionalHarmony(chordProgression);
        this.strOutput += harmony.getStrOutput();
        this.strOutput += decodeBass(harmony);
        this.strOutput += decodeHarmonicSupport(harmony,4);
    }

    private String decodeHarmonicSupport(Harmony harmony,int length){
        String result = "";
        Arpeggios arpeggios = new Arpeggios("");
        ArrayList<String> harmonicSupport = parsedSong.get(1);
        harmonicSupport.remove(0);


//        String[] objects = harmonicSupport.toArray();
        int harmonicIndex=0;
        String[] notes = new String[4];
        for (int i = 0; i < harmony.getChordProgression().size(); i++){
              Integer[] chord = Arrays.copyOfRange(harmony.getChords().get(harmony.getChordProgression().get(i)),
                                      0, 4);


              for(int j = 0; j < length; j++){
                  notes[0]= harmonicSupport.get(harmonicIndex).substring(0,2);
                  notes[1]= harmonicSupport.get(harmonicIndex + 1).substring(0,2);
                  notes[2]= harmonicSupport.get(harmonicIndex + 2).substring(0,2);
                  notes[3]= harmonicSupport.get(harmonicIndex + 3).substring(0,2);
                  result += arpeggios.decodeRandomInput(notes,chord);
                  harmonicIndex = harmonicIndex + 8;
              }
        }
        return result;
    }

    private String decodeBass(Harmony harmony){
        System.out.println();
        return harmony.decodeBassline(parsedSong.get(3),2);
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

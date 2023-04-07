package main.composition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Arpeggios {
    String patternString = "";

    public Arpeggios(String patternString) {

        this.patternString = patternString;
    }

    public String getPatternString() {
        return patternString;
    }

    public void setPatternString(String patternString) {
        this.patternString = patternString;
    }

    public void arpegiateUpDownRepeated(String[] notes, int length){
        for(int i = 0; i < length; i++){
            patternString+= notes[0] + " ";
            patternString+= notes[1]+ " ";
            patternString+= notes[2]+ " ";
            patternString+= notes[3]+ " ";
            patternString+= notes[3]+ " ";
            patternString+= notes[2]+ " ";
            patternString+= notes[1]+ " ";
            patternString+= notes[0]+ " ";
        }
    }

    public void arpegiateUpDown(String[] notes, int length){
        for(int i = 0; i < length; i++){
            patternString+= notes[0] + " ";
            patternString+= notes[1]+ " ";
            patternString+= notes[2]+ " ";
            patternString+= notes[3]+ " ";
            patternString+= notes[2]+ " ";
            patternString+= notes[1]+ " ";
        }
    }

    public void arpegiateUp(String[] notes, int length){
        for(int i = 0; i < length; i++){
            patternString+= notes[0] + " ";
            patternString+= notes[1]+ " ";
            patternString+= notes[2]+ " ";
            patternString+= notes[3]+ " ";
        }
    }

    public void arpegiateDown(String[] notes, int length){
        for(int i = 0; i < length; i++){
            patternString+= notes[3]+ " ";
            patternString+= notes[2]+ " ";
            patternString+= notes[1]+ " ";
            patternString+= notes[0]+ " ";
        }
    }

    public void arpegiateRandom(String[] notes, int length){
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        for(int i = 0; i < length; i++){
            Collections.shuffle(numbers, random);
            patternString+= notes[numbers.get(0)]+ " ";
            patternString+= notes[numbers.get(1)]+ " ";
            patternString+= notes[numbers.get(2)]+ " ";
            patternString+= notes[numbers.get(3)]+ " ";
        }

    }


}

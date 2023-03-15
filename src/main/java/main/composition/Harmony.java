package main.composition;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

public class Harmony {

    List<Integer[]> chords = new ArrayList<>();

    public List<Integer[]> getChords() {
        return chords;
    }

    public void setChords(List<Integer[]> chords) {
        this.chords = chords;
    }

    public List<Integer> getChordProgression() {
        return chordProgression;
    }

    public void setChordProgression(List<Integer> chordProgression) {
        this.chordProgression = chordProgression;
    }

    List<Integer> chordProgression = new ArrayList<>();
    Integer[] key;
    String strInput;
    String strOutput;
    public String getStrInput() {
        return strInput;
    }

    public void setStrInput(String strInput) {
        this.strInput = strInput;
    }

    public String getStrOutput() {
        return strOutput;
    }

    public void setStrOutput(String strOutput) {
        this.strOutput = strOutput;
    }

    public Harmony(Integer [] key, String strInput) {
        this.key=key;
        this.strInput= strInput;
        this.generateChords();
    }

    //major and minor and chr scales are using the same intervals
    // all chords have the same intervals
    private void generateChords(){
        //todo give me 8 chord notes pls
        int offset = 0;
        if(key[0]>5){
            offset = 7;
        }
        for(int i = 35; i < 42; i++){
            Integer [] chord = new Integer[8];
            if(i < 38){
                chord[0] = key[i-offset];
                chord[1] = key[i + 2-offset];
                chord[2] = key[i + 4-offset];
                chord[3] = key[i - 3-offset];
                chord[4] = key[i - 5-offset];
                chord[5] = key[i + 7-offset];
                chord[6] = key[i + 9-offset];
                chord[7] = key[i + 11-offset];
            }else {
                int a = 7;
                chord[0] = key[i-a];
                chord[1] = key[i + 2-a];
                chord[2] = key[i + 4-a];
                chord[3] = key[i - 3-a];
                chord[4] = key[i - 5-a];
                chord[5] = key[i + 7-a];
                chord[6] = key[i + 9-a];
                chord[7] = key[i + 11-a];
            }
            this.chords.add(chord);
        }
    }
    public void printChords(){
        int counter = 0;
        for (Integer[] chord : this.chords){
            System.out.println("chrod"+counter++);
            for (Integer i : chord){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private int determineFollowingChord(int previousChord) throws Exception{
        switch (previousChord){
            case 0,2,5:
                int choice = this.getChoice(2);
                System.out.println("choice "+choice);
                switch (choice){
                    case 0:
                        return 4;
                    case 1:
                        return 6;
                    case 2:
                        return 1;
                    case 3:
                        return 3;
                    default:
                        throw new Exception("unknown choice");
                }
            case 1,3:
                choice = this.getChoice(2);
                System.out.println("choice "+choice);
                switch (choice){
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 5;
                    case 3:
                        return 3;
                    default:
                        throw new Exception("unknown choice");
                }
            case 4,6:
                choice = this.getChoice(2);
                System.out.println("choice "+choice);
                switch (choice){
                    case 0:
                        return 1;
                    case 1:
                        return 3;
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    default:
                        throw new Exception("unknown choice");
                }
            default:
                throw new Exception("unknown chord");
        }
    }

    public void createFunctionalHarmony(int numberOfChords)throws Exception {
        this.chordProgression.add(0);
        int iterator=0;

        while ((iterator<numberOfChords) || (this.chordProgression.get(this.chordProgression.size()-1) != 0)){
            this.chordProgression.add(determineFollowingChord(this.chordProgression.get(this.chordProgression.size()-1)));
            iterator++;
        }

    }

    public void decodeFunctionalHarmony(List<Integer> chordProgression) throws Exception{
        StringBuilder stringBuilder = new StringBuilder(strOutput);
        int current = 0;
     while (current<chordProgression.size()){
         switch (chordProgression.get(current)){

         case 0,2,5:
             switch (chordProgression.get(current+1)){
                 case 4:
                     stringBuilder.append("00");
                     break;
                 case 6:
                     stringBuilder.append("01");
                     break;
                 case 1:
                     stringBuilder.append("10");
                     break;
                 case 3:
                     stringBuilder.append("11");
                     break;
                 default:
                     throw new Exception("unknown choice");
             }
             break;
         case 1,3:
             switch (chordProgression.get(current+1)){
                 case 0:
                     stringBuilder.append("00");
                     break;
                 case 2:
                     stringBuilder.append("01");
                     break;
                 case 5:
                     stringBuilder.append("10");
                     break;
                 case 3:
                     stringBuilder.append("11");
                     break;
                 default:
                     throw new Exception("unknown choice");
             }
             break;
         case 4,6:
             switch (chordProgression.get(current+1)){
                 case 1:
                     stringBuilder.append("00");
                     break;
                 case 3:
                     stringBuilder.append("01");
                     break;
                 case 0:
                     stringBuilder.append("10");
                     break;
                 case 2:
                     stringBuilder.append("11");
                     break;
                 default:
                     throw new Exception("unknown choice");
             }
             break;
         default:
             throw new Exception("unknown chord");
        }
         current++;
     }
     strOutput = stringBuilder.toString();
    }





    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }


}

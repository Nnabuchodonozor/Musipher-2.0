package main.composition;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rhytm {

    String strInput;
    String strOutput;

    public Rhytm(String strInput) {
        this.strInput = strInput;
    }

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
    // there has to be rule that only melody of sizes of measures will be added, not more not less.
    public String[] createRhytmValues(Integer[] melody, int syncopationStrength, List <Integer[]> chords, List<Integer> progression, int chordLength){
        int measureCounter = 0;
        int chordCounter = 0;

        List<String> resultList = new ArrayList<>();
        if (syncopationStrength==1) {
            int beatCounter = 0;
            int i = 0;
            while (i < melody.length-3) {
                if((beatCounter==0) || (beatCounter==8)){
                    int choice1 = getChoice(3);
                    resultList.add( chords.get(progression.get(chordCounter) )[choice1]  + "q ");

//                    resultList.add( melody[i]  + "q ");
//                    i++;
                }else {
                    int choice = getChoice(2);
                    switch (choice){
                        case 0:
                            resultList.add( melody[i] + "i ");
                            i++;
                            resultList.add( melody[i] + "s ");
                            i++;
                            resultList.add( melody[i] + "s ");
                            i++;
                            break;
                        case 1:
                            resultList.add( melody[i] + "i. ");
                            i++;
                            resultList.add( melody[i] + "s ");
                            i++;
                            break;
                        case 2:
                            resultList.add( melody[i] + "s ");
                            i++;
                            resultList.add( melody[i] + "s ");
                            i++;
                            resultList.add( melody[i] + "i ");
                            i++;
                            break;
                        case 3:
                            resultList.add( melody[i] + "i ");
                            i++;
                            resultList.add( melody[i] + "i ");
                            i++;
                            break;
                        default:
                            break;
                    }
                }
                beatCounter+=4;
                if (beatCounter==16) {
                    beatCounter = 0;
                    measureCounter++;
                    if(measureCounter==chordLength){
                        chordCounter++;
                        chordLength = chordLength + chordLength;
                    }
                }
            }

        }

        return resultList.toArray(new String[0]);
    }



    public int getChoice(int length){
        String a = strInput.substring(0,length);
        System.out.print(a);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }


}

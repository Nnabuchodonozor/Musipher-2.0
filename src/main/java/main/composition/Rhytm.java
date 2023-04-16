package main.composition;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rhytm {

    String strInput;
    String strOutput;
    Integer [] key;

    public Rhytm(String strInput, Integer[] key) {

        this.strInput = strInput;
        this.key = key;
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
    // q = 4
    // i = 2
    // i. = 3
    // s = 1

    public String[] createRhytmValues(Integer[] melody, int syncopationStrength, List <Integer[]> chords, List<Integer> progression, int chordLength){
        int measureCounter = 0;
        int chordCounter = 0;

        List<String> resultList = new ArrayList<>();
            int beatCounter = 0;
            int i = 0;
            while (i < melody.length-3) {

                if(determineSync(beatCounter,syncopationStrength)){
                    int choice1 = getChoice(2);
                    resultList.add( chords.get(progression.get(chordCounter) )[choice1]  + "i ");

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
//                            resultList.add( melody[i] + "s ");
//                            i++;
                            break;
                        case 1:
                            resultList.add( melody[i] + "s ");
                            i++;
                            resultList.add( melody[i] + "s ");
                            i++;
                            break;
                        case 2:
//                            resultList.add( melody[i] + "s ");
//                            i++;
                            resultList.add( melody[i] + "s ");
                            i++;
                            resultList.add( melody[i] + "i ");
                            i++;
                            break;
                        case 3:
                            resultList.add( melody[i] + "i ");
                            i++;
//                            resultList.add( melody[i] + "i ");
//                            i++;
                            break;
                        default:
                            break;
                    }
                }
                beatCounter+=2;
                if (beatCounter==16) {
                    beatCounter = 0;
                    measureCounter++;
                    if(measureCounter==chordLength){
                        chordCounter++;
                        chordLength = chordLength + chordLength;
                    }
                }
            }


        return resultList.toArray(new String[0]);
    }

    public String[] createRhytmicisedMelody(int beats,int syncopationStrength, List <Integer[]> chords, List<Integer> progression, int chordLength, Instrument instrument){
        Melody melody = new Melody(key);
        int measureCounter = 0;
        int chordCounter = 0;

        List<String> resultList = new ArrayList<>();
        int beatCounter = 0;
        for (int i = 0; i < beats;i++){

            // in this decision I can get 2 bits
            if(determineSync(beatCounter,syncopationStrength)){
                int choice1 = getChoice(2);
                Integer note = chords.get(progression.get(chordCounter) )[choice1];
                melody.setLastNote(note);
                resultList.add(  note + "i ");
                beatCounter += 2;
                continue;
//                    resultList.add( melody[i]  + "q ");
//                    i++;
            }else {
                int choice = getChoice(2);
                switch (choice){
                    case 0:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();
                        break;
                    case 1:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s. ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "t ") );
                        this.strInput= melody.getStrInput();
                        break;
                    case 2:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "t ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s. ") );
                        this.strInput= melody.getStrInput();
                        break;
                    case 3:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "i ") );
                        this.strInput= melody.getStrInput();

                        break;
                    default:
                        break;
                }
            }
            beatCounter+=2;
            if (beatCounter==16) {
                beatCounter = 0;
                measureCounter++;
                if(measureCounter==chordLength){
                    chordCounter++;
                    chordLength = chordLength + chordLength;
                }
            }


        }
        return  resultList.toArray(new String[0]);

    }


    private boolean determineSync(int beatCounter ,int s){
        if(s == 0){
            if (beatCounter == 0)
                return true;
            else
                return false;
        }else if(s == 1) {
            if ((beatCounter == 0) || (beatCounter == 8))
                return true;
            else
                return false;
        }else if (s == 2)
        {
            if ((beatCounter == 0) || (beatCounter == 8) || (beatCounter == 12))
                return true;
            else
                return false;
        }else if(s == 3){
                return true;
        }else
            return false;
    }


    public int getChoice(int length){
        String a = strInput.substring(0,length);
        System.out.print(a);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }


}

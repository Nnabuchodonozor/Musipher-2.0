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

    public String[] createRhytmicisedMelody(int beats,int syncopationStrength, List <Integer[]> chords, List<Integer> progression, int chordLength, Instrument instrument, int chordCounter,  int rhytmStrength){
        Melody melody = new Melody(key);
        int measureCounter = 0;

        List<String> resultList = new ArrayList<>();
        int beatCounter = 0;
        for (int i = 0; i < beats;i++){

            // in this decision I can get 2 bits
            if(determineSync(beatCounter,syncopationStrength)){
                int choice1 = getChoice(2);
                Integer note = chords.get(progression.get(chordCounter) )[choice1];
                melody.setLastNote(note);
                resultList.add(  note + "q ");
                beatCounter += 4;
                continue;
//                    resultList.add( melody[i]  + "q ");
//                    i++;
            }else {
                int choice = getChoice(rhytmStrength);
                switch (choice){
                    case 0:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "i ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "i ") );
                        this.strInput= melody.getStrInput();
                        break;
                    case 1:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "i ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();
                        break;
                    case 2:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();
                        break;
                    case 3:
                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "i. ") );
                        this.strInput= melody.getStrInput();

                        resultList.add(new String(melody.addRandomMelody("", instrument, this.strInput) + "s ") );
                        this.strInput= melody.getStrInput();
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
//                    chordCounter++;
//                    chordLength = chordLength + chordLength;
                }
            }
        }
        return  resultList.toArray(new String[0]);
    }

    public String decodeRhytmicisedMelody(int beats, int syncopationStrength, Integer[] chord, ArrayList<String> melody){
        Melody melody1 = new Melody(key);
        List<ArrayList<Integer>> notes = this.divideMelody(melody,beats);
        String result = "";
        int beatCounter = 0;
        int noteCounter = 0;

        for (int i = 0; i < beats; i++) {
            if (determineSync(beatCounter, syncopationStrength)) {
                Integer note = notes.get(i).get(0);
                result += this.getChoiceString(this.chordChoice(note, chord), 2);
                melody1.setLastNote(note);
//
            } else {
                if (notes.get(i).size() == 2) {
                    result += "0";
                    result += melody1.decodeRandomMelody(notes.get(i).get(0));
                    result += melody1.decodeRandomMelody(notes.get(i).get(1));

                } else {
                    result += "1";
                    result += melody1.decodeRandomMelody(notes.get(i).get(0));
                    result += melody1.decodeRandomMelody(notes.get(i).get(1));
                    result += melody1.decodeRandomMelody(notes.get(i).get(2));
                }
            }
            beatCounter += 4;
            if (beatCounter == 16) {
                beatCounter = 0;
            }
        }
        return result;
    }


    private List<ArrayList<Integer>> divideMelody(ArrayList<String> melody, int beats){
        List<ArrayList<Integer>> result = new ArrayList<>();
        int c = 0;
        int noteCounter = 0;
        for (int i = 0; i < beats; i++){
            c=0;
            ArrayList<Integer> beat = new ArrayList<>();
            while (c < 4){
                String s = melody.get(noteCounter).substring(2,melody.get(noteCounter).length()-1);
                switch (s){
                    case "q":
                        c = c + 4;
                        beat.add(Integer.parseInt(melody.get(noteCounter).substring(0,2)));
                        break;
                    case "i":
                        c = c + 2;
                        beat.add(Integer.parseInt(melody.get(noteCounter).substring(0,2)));
                        break;
                    case "s":
                        c = c + 1;
                        beat.add(Integer.parseInt(melody.get(noteCounter).substring(0,2)));
                        break;
                    case "i.":
                        c = c + 3;
                        beat.add(Integer.parseInt(melody.get(noteCounter).substring(0,2)));
                        break;
                }
                noteCounter++;
            }
            result.add(beat);

        }

        return result;
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
//        System.out.print(a);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }

    private int chordChoice(Integer note, Integer[] chord){
        for (int i = 0; i< chord.length; i++){
            if(note==chord[i])
                return i;
        }
        return -1;
    }


    private String getChoiceString(int choice, int length){
        String s= Integer.toBinaryString(choice);
        while (s.length()<length){
            s = "0"+s;
        }
        return s;
    }

}

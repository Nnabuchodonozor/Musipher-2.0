package main.composition;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drums {

    String strInput;
    public Drums(String strInput) {
        this.strInput = strInput;
    }

    public Rhythm generateDrums(){
        Rhythm rhythm = new Rhythm();
        rhythm.addLayer(this.kicks());
        rhythm.addLayer(this.snares());
//        rhythm.addLayer(this.hiHat());
//        rhythm.addLayer(this.clap());
//        rhythm.addLayer(this.crash());
        return rhythm;
    }

    public String kicks() {
        String kickLayer = "";
        //oO
        //input: 0 1 2 3 ->
        for(int i = 0; i < 16; i++) {
            if (this.getChoice(1) == 1) {
                if (this.getChoice(1) == 1){
                    kickLayer += "O";
                }else {
                    kickLayer += "o";
                }
            } else {
                kickLayer += ".";
            }
        }
        System.out.println(kickLayer);
        return kickLayer;
    }

    public String snares() {
        //sS
        String snaresLayer = "";
        //oO
        //input: 0 1 2 3 ->
        for(int i = 0; i < 16; i++) {
            if (this.getChoice(1) == 1) {
                if (this.getChoice(1) == 1){
                    snaresLayer += "S";
                }else {
                    snaresLayer += "s";
                }
            } else {
                snaresLayer += ".";
            }
        }
        System.out.println(snaresLayer);
        return snaresLayer;
    }

    public String hiHat() {
        //`^
        String hiHatLayer = "";
        //oO
        //input: 0 1 2 3 ->
        for(int i = 0; i < 16; i++) {
            if (this.getChoice(1) == 1) {
                if (this.getChoice(1) == 1){
                    hiHatLayer += "^";
                }else {
                    hiHatLayer += "`";
                }
            } else {
                hiHatLayer += ".";
            }
        }
        System.out.println(hiHatLayer);
        return hiHatLayer;

    }

    public String crash() {
        //*+
        String crashLayer = "";
        //oO
        //input: 0 1 2 3 ->
        for(int i = 0; i < 16; i++) {
            if (this.getChoice(1) == 1) {
                if (this.getChoice(1) == 1){
                    crashLayer += "+";
                }else {
                    crashLayer += "*";
                }
            } else {
                crashLayer += ".";
            }
        }
        System.out.println(crashLayer);
        return crashLayer;

    }

    public String clap() {
        //xX
        String clapLayer = "";
        //oO
        //input: 0 1 2 3 ->
        for(int i = 0; i < 16; i++) {
            if (this.getChoice(1) == 1) {
                if (this.getChoice(1) == 1){
                    clapLayer += "X";
                }else {
                    clapLayer += "x";
                }
            } else {
                clapLayer += ".";
            }
        }
        System.out.println(clapLayer);
        return clapLayer;

    }

    public String getStrInput() {
        return strInput;
    }

    public void setStrInput(String strInput) {
        this.strInput = strInput;
    }
    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }
//
//    .Ooo.O.oO.o.o.O.
//    .sSs.S.S..S..SS.

    // parsed lines
    // 0 bass
    // 1 snare
    // 2 clap
    // 3 crash
    // 4 hat
    public void parseDrums(String unparsedDrums){
        List<String> drumStreams = Arrays.asList("", "", "", "", "");

        unparsedDrums = unparsedDrums.replaceAll("\\[BASS_DRUM\\]i","O");
        unparsedDrums = unparsedDrums.replaceAll("\\[BASS_DRUM\\]s","o");
        unparsedDrums = unparsedDrums.replaceAll("\\[ACOUSTIC_SNARE\\]i","S");
        unparsedDrums = unparsedDrums.replaceAll("\\[ACOUSTIC_SNARE\\]s","s");
        unparsedDrums = unparsedDrums.replaceAll("\\[HAND_CLAP\\]i","X");
        unparsedDrums = unparsedDrums.replaceAll("\\[HAND_CLAP\\]s","x");
        unparsedDrums = unparsedDrums.replaceAll("\\[CRASH_CYMBAL_1\\]i","*");
        unparsedDrums = unparsedDrums.replaceAll("\\[CRASH_CYMBAL_1\\]s","+");
        unparsedDrums = unparsedDrums.replaceAll("\\[PEDAL_HI_HAT\\]i","^");
        unparsedDrums = unparsedDrums.replaceAll("\\[PEDAL_HI_HAT\\]s","`");
        System.out.println(unparsedDrums);

        String[] parsedDrums = unparsedDrums.split(" ");
        for(int i = 0; i < parsedDrums.length; i++){
            switch (parsedDrums[i]){
                case "Ri":
                    for (int j = 0; j < 5; j++){
                         drumStreams.set(j, drumStreams.get(j) + "__");
                    }
                    break;
                case "Rs":
                    for (int j = 0; j < 5; j++){
                        drumStreams.set(j, drumStreams.get(j) + "_");
                    }
                    break;
                case "Ri.":
                    for (int j = 0; j < 5; j++){
                        drumStreams.set(j, drumStreams.get(j) + "___");
                    }
                    break;
                case "o":
                    drumStreams.set(0, drumStreams.get(0)+"o");
                    break;
                case "O":
                    drumStreams.set(0, drumStreams.get(0)+"O");
                    break;
                case "s":
                    drumStreams.set(1, drumStreams.get(1)+"s");
                    break;
                case "S":
                    drumStreams.set(1, drumStreams.get(1)+"S");
                    break;
                case "x":
                    drumStreams.set(2, drumStreams.get(2)+"x");
                    break;
                case "X":
                    drumStreams.set(2, drumStreams.get(2)+"X");
                    break;
                case "*":
                    drumStreams.set(3, drumStreams.get(3)+"*");
                    break;
                case "+":
                    drumStreams.set(3, drumStreams.get(3)+"+");
                    break;
                case "^":
                    drumStreams.set(4, drumStreams.get(4)+"_^");
                    break;
                case "`":
                    drumStreams.set(4, drumStreams.get(4)+"`");
                    break;
                default:
                    break;
            }
        }

        for (String s : drumStreams){
            System.out.println(s);
        }
    }




}

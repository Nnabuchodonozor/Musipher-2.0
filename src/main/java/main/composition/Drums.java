package main.composition;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

import java.io.File;

public class Drums {

    String strInput;
    public Drums(String strInput) {
        this.strInput = strInput;
    }

    public Rhythm generateDrums(){
        Rhythm rhythm = new Rhythm();
        rhythm.addLayer(this.kicks());
        rhythm.addLayer(this.snares());
        rhythm.addLayer(this.hiHat());
        rhythm.addLayer(this.clap());
        rhythm.addLayer(this.crash());
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


}

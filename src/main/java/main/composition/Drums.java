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
    StringBuilder strOutput = new StringBuilder();
    DrumBank drumBank = new DrumBank();
    public Drums(String strInput) {
        this.strInput = strInput;

    }

    public Rhythm concatRhytm(Rhythm rhythm1, Rhythm rhythm2){
        List<String> result = new ArrayList<>();
        for(int i = 0; i < rhythm1.getLayers().size(); i++){
            result.add( rhythm1.getLayer(i) + rhythm2.getLayer(i));
        }
        Rhythm r = new Rhythm();
        r.setLayers(result);
        return r;
    }


    //this is method for generating drums layer for all sounds based on input
    private String drumsBasedOnInput(String i, String s, int lengthOfLayer){
        String result ="";
        for(int j = 0; j < lengthOfLayer; j++) {
            if (this.getChoice(1) == 1){
                result += i;
            }else {
                result += s;
            }
        }
        return  result;
    }
    //this is method for getting decomposed output from drum layers
    private void outputBasedOnDrums(String layer, String s, int lengthOfLayer){
        switch (s){
            case "*","+":
                s="*";
                break;
            case "^","`":
                s="`";
                break;
            default:
                break;
        }

        for(int j = 0; j < lengthOfLayer; j++){
            String choice = layer.substring(0,1);
            layer = layer.substring(1);
            if (choice.equals(s))
                strOutput.append(0);
            else
                strOutput.append(1);
        }
    }

    public String getStroutput(){
        return strOutput.toString();
    }

    public Rhythm generateDrums(int layerStrength,int version, int first){
        String layer1 = this.kicks(layerStrength);
        String layer2 = this.snares(layerStrength);
        String layer3 = this.clap(layerStrength);
        String layer4 = this.crash(layerStrength);
        String layer5 = this.hiHat(layerStrength);
        String[] layers = new String[5];
        layers[0] = layer1;
        layers[1] = layer2;
        layers[2] = layer3;
        layers[3] = layer4;
        layers[4] = layer5;
        String[] finalLayers = drumBank.fillLayers(layers,version,first);
        Rhythm rhythm = new Rhythm();

        for(String s : finalLayers){
            System.out.println("adapted "+s );
            rhythm.addLayer(s);
        }
//        rhythm.addLayer(this.kicks());
//        rhythm.addLayer(this.snares());
//        rhythm.addLayer(this.hiHat());
//        rhythm.addLayer(this.clap());
//        rhythm.addLayer(this.crash());
        return rhythm;
    }

    public String kicks(int layerStrength) {
        String kickLayer = drumsBasedOnInput("O","o",layerStrength);
        //oO

        System.out.println(kickLayer);
        return kickLayer;
    }

    public String snares(int layerStrength) {
        //sS
        String snaresLayer = drumsBasedOnInput("S","s",layerStrength);

        System.out.println(snaresLayer);
        return snaresLayer;
    }

    public String hiHat(int layerStrength) {
        //`^
        String hiHatLayer = drumsBasedOnInput("^","`",layerStrength);
        System.out.println(hiHatLayer);
        return hiHatLayer;

    }

    public String crash(int layerStrength) {
        //*+
        String crashLayer = drumsBasedOnInput("+","*",layerStrength/4);
        System.out.println(crashLayer);
        return crashLayer;

    }

    public String clap(int layerStrength) {
        //xX
        String clapLayer = drumsBasedOnInput("X","x",layerStrength/2);
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
//    OooOoOooO
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
//                case "Ri":
//                    for (int j = 0; j < 5; j++){
//                         drumStreams.set(j, drumStreams.get(j) + "__");
//                    }
//                    break;
//                case "Rs":
//                    for (int j = 0; j < 5; j++){
//                        drumStreams.set(j, drumStreams.get(j) + "_");
//                    }
//                    break;
//                case "Ri.":
//                    for (int j = 0; j < 5; j++){
//                        drumStreams.set(j, drumStreams.get(j) + "___");
//                    }
//                    break;
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
                    drumStreams.set(4, drumStreams.get(4)+"^");
                    break;
                case "`":
                    drumStreams.set(4, drumStreams.get(4)+"`");
                    break;
                default:
                    break;
            }
        }

        List<String> correctlyConnectedDrums = Arrays.asList("","","","","","","","","","","","","","","");
            //necessary evil

        // o O
            correctlyConnectedDrums.set(0, drumStreams.get(0).substring(0,8));
            correctlyConnectedDrums.set(5, drumStreams.get(0).substring(8,24));
            correctlyConnectedDrums.set(10, drumStreams.get(0).substring(24,56));
        // s S
        correctlyConnectedDrums.set(1, drumStreams.get(1).substring(0,8));
        correctlyConnectedDrums.set(6, drumStreams.get(1).substring(8,24));
        correctlyConnectedDrums.set(11, drumStreams.get(1).substring(24,56));
    //  x X
        correctlyConnectedDrums.set(2, drumStreams.get(2).substring(0,4));
        correctlyConnectedDrums.set(7, drumStreams.get(2).substring(4,12));
        correctlyConnectedDrums.set(12, drumStreams.get(2).substring(12,28));
  //  * +
        correctlyConnectedDrums.set(3, drumStreams.get(3).substring(0,2));
        correctlyConnectedDrums.set(8, drumStreams.get(3).substring(2,6));
        correctlyConnectedDrums.set(13, drumStreams.get(3).substring(6,14));
//    ^ `
        correctlyConnectedDrums.set(4, drumStreams.get(4).substring(0,8));
        correctlyConnectedDrums.set(9, drumStreams.get(4).substring(8,24));
        correctlyConnectedDrums.set(14, drumStreams.get(4).substring(24,56));



        for (String s : correctlyConnectedDrums){
            if (!s.isEmpty()) {
                System.out.println(s);
                this.outputBasedOnDrums(s, s.substring(0, 1).toLowerCase(),s.length());
            }
        }
    }





}

package main.utils;


import main.composition.Conductor;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.jfugue.midi.MidiFileManager.loadPatternFromMidi;


public class MidiUtils {

    private static final String[] jfugueMelodyNotes = {"C0","C#0","D0", "Eb0", "E0" ,"F0", "F#0", "G0", "G#0", "A0", "Bb0", "B0",
            "C1","C#1","D1", "Eb1", "E1" ,"F1", "F#1", "G1", "G#1", "A1", "Bb1", "B1",
            "C2","C#2","D2", "Eb2", "E2" ,"F2", "F#2", "G2", "G#2", "A2", "Bb2", "B2",
            "C3","C#3","D3", "Eb3", "E3" ,"F3", "F#3", "G3", "G#3", "A3", "Bb3", "B3",
            "C4","C#4","D4", "Eb4", "E4" ,"F4", "F#4", "G4", "G#4", "A4", "Bb4", "B4",
            "C5","C#5","D5", "Eb5", "E5" ,"F5", "F#5", "G5", "G#5", "A5", "Bb5", "B5",
            "C6","C#6","D6", "Eb6", "E6" ,"F6", "F#6", "G6", "G#6", "A6", "Bb6", "B6",
            "C7","C#7","D7", "Eb7", "E7" ,"F7", "F#7", "G7", "G#7", "A7", "Bb7", "B7",
            "C8","C#8","D8", "Eb8", "E8" ,"F8", "F#8", "G8", "G#8", "A8", "Bb8", "B8"};
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public void composeMIDI(byte[] encryptedData) throws IOException {
        String strInput = createBinaryString(encryptedData);
        Pattern mainPattern = createMusic(strInput);
        MidiFileManager.savePatternToMidi(mainPattern, new File("miusik.mid"));
    }

    public byte[] decomposeMIDI(String path) throws InvalidMidiDataException, IOException {
        Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File(path));
        String strInput = decodeMusic(mainPattern);
        byte[] parsedBinaryString = parseBinaryString(strInput);


        return parsedBinaryString;
    }

    private String decodeMusic(Pattern mainPattern){
        return "1010010101010110";
    }

    public void evaluateDeciphering(String original, String deciphered){
        System.out.println(ANSI_RED + "Decompose evaluation function "+ ANSI_RESET);
        if (original.length() == deciphered.length())
            System.out.println("length is the same");
        else
            System.out.println("lenght does not match");
        System.out.println(original);
        char[] originalC = original.toCharArray();
        char[] decipheredC = deciphered.toCharArray();

        for (int i = 0; i < decipheredC.length; i++) {
            if(decipheredC[i]==originalC[i]){
                System.out.print(ANSI_GREEN + decipheredC[i] + ANSI_RESET);
            }else {
                System.out.print(ANSI_RED + decipheredC[i] + ANSI_RESET);
            }
        }
        System.out.println();
    }

    private String createBinaryString(byte[] encryptedData){
            String binaryString ="";
            for(int i = 0; i < encryptedData.length; i++) {
                binaryString += Integer.toBinaryString((encryptedData[i] & 0xFF) + 0x100).substring(1);
            }
            return binaryString;
    }

    private byte[] parseBinaryString(String strInput){
        byte[] bts = new byte[(strInput.length() / 8)+1];
        int openBytesCounter = 0;
        while (strInput.length()%8!=0){
            strInput+="0";
        }

        while(strInput.length()!=0){
            Integer tempinteger = Integer.parseInt(strInput.substring(0,8), 2);
            byte b = tempinteger.byteValue();
            strInput=strInput.substring(8);
            bts[openBytesCounter]=b;
            openBytesCounter++;
        }
        return bts;
    }

    private Pattern createMusic(String strInput){
        Conductor conductor = new Conductor(strInput);
        return new Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq");
    }



    // hashMap would be faster but whatever
    public List<ArrayList<String>> parseIncomingNotes(String incomingNotes){
        List<ArrayList<String>> structuredMelody = new ArrayList<>();
        ArrayList<String> layer = new ArrayList<>();

        String[] tokenizedNotes = incomingNotes.split(" ");
        StringBuilder parsedMelody=new StringBuilder();
        int c= 0;
        for (int i = 1; i < tokenizedNotes.length; i++){
            if(tokenizedNotes[i].startsWith("V")) {
                structuredMelody.add(layer);
                layer = new ArrayList<>();
                continue;
            }else if(tokenizedNotes[i].startsWith("I")){
                layer.add(tokenizedNotes[i]);
                continue;
            }else {
                if (tokenizedNotes[i].endsWith(".")) {
                    c = 2;
                } else {
                    c = 1;
                }
            }
            for(int j = 0; j < jfugueMelodyNotes.length; j ++){
                if(jfugueMelodyNotes[j].equals(tokenizedNotes[i].substring(0,tokenizedNotes[i].length()-c))) {
                    layer.add(j + tokenizedNotes[i].substring(tokenizedNotes[i].length() - c) + " ");
//                    parsedMelody.append(j).append(tokenizedNotes[i].substring(tokenizedNotes[i].length() - c)).append(" ");
                    break;
                }
            }
        }
        //added last layer
        structuredMelody.add(layer);
        return structuredMelody;
    }



}

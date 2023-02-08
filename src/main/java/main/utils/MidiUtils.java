package main.utils;


import main.composition.Conductor;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

import static org.jfugue.midi.MidiFileManager.loadPatternFromMidi;

public class MidiUtils {

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



}

package main;


import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.imageio.IIOException;
import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

import static org.jfugue.midi.MidiFileManager.loadPatternFromMidi;

public class MidiUtils {


    public void composeMIDI(byte[] encryptedData) throws IOException {
        String strInput = createBinaryString(encryptedData);
        Pattern mainPattern = createMusic(strInput);
        MidiFileManager.savePatternToMidi(mainPattern, new File("miusik.mid"));
    }

    public byte[] decomposeMIDI(String path) throws InvalidMidiDataException, IOException {
        Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File(path));
        String strInput = decodeMusic(mainPattern);
        return null;
    }

    private String decodeMusic(Pattern mainPattern){
        return "1010010101010110";
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
        return new Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq");
    }
}

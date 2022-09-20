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
        Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
        return null;
    }

    private String createBinaryString(byte[] encryptedData){
            String base32String ="";
            for(int i = 0; i < encryptedData.length; i++) {
                base32String += Integer.toBinaryString((encryptedData[i] & 0xFF) + 0x100).substring(1);
            }
            return base32String;
    }

    private Pattern createMusic(String strInput){
        return new Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq");
    }
}

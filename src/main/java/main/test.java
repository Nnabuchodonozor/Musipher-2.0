package main;


import main.utils.MidiUtils;
import main.utils.SemanticUtils;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.io.File;

public class test {
    public static void main(String[] args) {
        try {

            Pattern mainPattern = new Pattern();
            // add a single note
            String note = "60q ";
            String instrument ="I32 ";
            String voice = "V1";
            mainPattern.add(instrument);
            mainPattern.add(note);

            //add chord with duration
            String chord = "60maj/1 ";
            String chord2 = "60maj7/2 ";
            String chord3 = "60maj7^^ ";
            //marker
            String marker = "#chorus ";
            mainPattern.add(marker);
            //ties between notes
            String tie = " - ";
            mainPattern.add(chord);
            mainPattern.add(chord2);
            mainPattern.add(voice);
            mainPattern.add(chord3);
            mainPattern.clear();
            mainPattern.add("V0 I0  E5s D#5s | E5s D#5s E5s B4s D5s C5s " +
                            "V1 I40 E4i       | C4w                     " +
                            "V3 I19 C3qq      | E4 q " +
                            "V9 38q 38q 38q 38q | 38q 38q");
            System.out.println(mainPattern.toString());
            MidiFileManager.savePatternToMidi(mainPattern, new File("miusik.mid"));
            Pattern loadedPattern = MidiFileManager.loadPatternFromMidi(new File( "miusik.mid"));
            System.out.println(loadedPattern.toString());

//            SemanticUtils semanticUtils = new SemanticUtils();
//            semanticUtils.encryptToMIDIFromFile("image.jpg","password");
//            semanticUtils.decryptFromMIDIToFile("encryptedBytes","password");


            //            semanticUtils.encryptToMIDIFromText("text","password");

//            File file = new File("song1.mp3"); // Sorrow.mp3 is the local mp3 music needs to be sent
//            FileInputStream loc = new FileInputStream(file);
//            byte[] rawMp3 = new byte[(int)file.length()];
//            loc.read(rawMp3);
//            System.out.println(rawMp3);
//            File file2 = new File("song2.mp3");
//            FileOutputStream fos = new FileOutputStream(file2);
//            fos.write(rawMp3);
//            fos.flush();
//            fos.close();



//      String openText = "this is sacred message";
//      I need to provide a seed
//            String seed = "seed";
            //iv should generate by itself
//            semanticUtils.encryptToMIDIFromText(openText, seed);
//            semanticUtils.decryptFromMIDIToText("miusik.mid","encryptionKey");



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

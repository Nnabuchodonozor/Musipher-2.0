package main;


import main.composition.*;
import main.utils.MidiUtils;
import main.utils.SemanticUtils;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

import java.io.File;
import java.io.FileOutputStream;

public class test {



    public static void main(String[] args) {
        try {
            int b = 9;
            MidiUtils midiUtils = new MidiUtils();
            String strInput = "01110100110101101001001100101110" +
                    "01101100110011110010010001100100" +
                    "01111001101010011100011000011000" +
                    "11000001010010011110100101000000" +
                    "10110101001011100001010101000100";



            Key key = new Key(strInput);
            Integer [] a = key.generateKey();
            strInput = key.getStrInput();
            Melody melody = new Melody(a);

            // add 5 note random motif
            String patternString = "V0 ";
            for(int i = 0; i < 5; i++) {
                melody.addRandomMelody(patternString, null, strInput);
                strInput = melody.getStrInput();
                patternString=melody.getPatternString();
            }

            Motif motif = new Motif(patternString, patternString);
            motif.developPattern();
            patternString= motif.getPatternString();

            Pattern pattern = new Pattern();
            pattern.add(patternString);

            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
            System.out.println(mainPattern.toString());




//            SemanticUtils semanticUtils = new SemanticUtils();


//            Pattern mainPattern = new Pattern();
//            // add a single note
//            String note = "60q ";
//            String instrument ="I0 ";
//            String voice = "V1";
//            mainPattern.add(instrument);
//            mainPattern.add(note);
//
//            //chord progression
//            ChordProgression cp = new ChordProgression("I IV V") .eachChordAs("$0 $0 $0 $0 $1 $1 $0 $0 $2 $1 $0 $0");
//            mainPattern.add(cp.getPattern());
//            //add chord with duration
//            String chord = "60maj/1 ";
//            String chord2 = "60maj7/2 ";
//            String chord3 = "60maj7^^ ";
//            //marker
//            String marker = "#chorus ";
//            mainPattern.add(marker);
//            //ties between notes
//            String tie = " - ";
////            mainPattern.add(chord);
////            mainPattern.add(chord2);
////            mainPattern.add(voice);
////            mainPattern.add(chord3);
////            mainPattern.add(new ChordProgression("I IV V").setKey("Cmaj"));
//            mainPattern.add("72q :Arpeggiated(Cmaj7w) ");
////            mainPattern.add("C5q :PitchWheel(63,64)");
//            mainPattern.add("36W ");
////            mainPattern.clear();
////            mainPattern.add("V0 I0  E5s D#5s | E5s D#5s E5s B4s D5s C5s " +
////                            "V1 I40 E4i       | C4w                     " +
////                            "V3 I19 C3qq      | E4 q " +
////                            "V9 38q 38q 38q 38q | 38q 38q");
//


//            Player player = new Player();
//            player.play(rhythm.getPattern().repeat(4));
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
////      I need to provide a seed
//            String seed = "seed";
//            //iv should generate by itself
//            semanticUtils.encryptToMIDIFromText(openText, seed);
//            semanticUtils.decryptFromMIDIToText("miusik.mid","encryptionKey");


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

package main;


import main.composition.Arpeggios;
import main.composition.Drums;
import main.composition.Key;
import main.composition.Melody;
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

            //            Key key = new Key(strInput);
//            Integer [] a = key.generateKey();
//            strInput = key.getStrInput();
//            Melody melody = new Melody(a);
//
////            for(int i = 1; i < a.length; i++){
////                System.out.print(a[i] + " ");
////            }
//            String patternString = "V0 ";
//            for(int i = 0; i < 25; i++) {
//                melody.addRandomMelody(patternString, null, strInput);
//                strInput = melody.getStrInput();
//                patternString=melody.getPatternString();
//            }
            Pattern pattern = new Pattern();
//            pattern.add(melody.getPatternString());
//
//            patternString += "V1 ";
//            Arpeggios arpeggios = new Arpeggios(patternString);
//            arpeggios.upUp(new String[] {"C3q","E3q","G3q"} ,6);
//            pattern.add(arpeggios.getPatternString());


//
            Drums drums = new Drums(strInput);
            Rhythm rhythm = drums.generateDrums();
            strInput = drums.getStrInput();
            pattern.add(rhythm);
            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
            System.out.println(mainPattern.toString());
            drums.parseDrums(mainPattern.toString());

                // LYDIAN MODE

                // pitches: c c# d eb e  f f# g g# a bb b c
                //          0 1  2  3 4  5 6  7  8 9 10 11 12
                //    c lyd:
                //           0  2  4 6 7  9  11 12

//                if( (i % 12 == 1)||(i % 12 == 3)||(i % 12 == 5)||(i % 12 == 8)||(i % 12 == 10)  )
//                    System.out.print("");
//                else
//                    System.out.print(i+b + " ");



                                                    // PENTATONIC



            SemanticUtils semanticUtils = new SemanticUtils();


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
//            //rhytm
//
//            Rhythm rhythm = new Rhythm();
//            rhythm.addLayer("O.OO...O.OO....O");
//            rhythm.addLayer("....o.......o...");
//            rhythm.addLayer("^.`.^.`.^.`.^.`.");
//mainPattern.add(rhythm);
//
//            System.out.println(mainPattern.toString());
//            MidiFileManager.savePatternToMidi(mainPattern, new File("miusik.mid"));
//            Pattern loadedPattern = MidiFileManager.loadPatternFromMidi(new File( "miusik.mid"));
//            System.out.println(loadedPattern.toString());


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

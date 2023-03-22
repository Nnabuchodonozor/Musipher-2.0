package main;


import main.composition.*;
import main.utils.MidiUtils;
import main.utils.SemanticUtils;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class test {



    public static void main(String[] args) {
        try {
            MidiUtils midiUtils = new MidiUtils();
            String strInput = "00000000100110101101001001100101110" +
                    "01101100110011110010010001100100" +
                    "01111001101010011100011000011000" +
                    "11000001010010011110100101000000" +
                    "01101100110011110010010001100100" +
                    "01111001101010011100011000011000" +
                    "11000001010010011110100101000000" +
                    "01101100110011110010010001100100" +
                    "01111001101010011100011000011000" +
                    "11000001010010011110100101000000" +
                    "10110101001011100001010101000100";

            Key key = new Key(strInput);
            Integer[] a  = key.generateKey();
            strInput = key.getStrInput();

            Harmony harmony = new Harmony(a,strInput);

            harmony.createFunctionalHarmony(10);
            strInput = harmony.getStrInput();
            List<Integer> chordProgression = harmony.getChordProgression();
            List<Integer[]> chords = harmony.getChords();

            String patternString = "V0 I[Cello] ";
//            patternString += chords.get(0)[0] + "w+" + chords.get(0)[1] + "w+" + chords.get(0)[2] + "w+" + chords.get(0)[3] + "w ";
            Integer[] antedecent = new Integer[]{chords.get(0)[0],chords.get(0)[1],chords.get(0)[2],chords.get(0)[3]};
            Arpeggios arpeggios = new Arpeggios(patternString);

            for(int i = 1; i < chordProgression.size(); i++){
                String arpegi[] = new String[]{antedecent[0]+"q",antedecent[1]+"q",antedecent[2]+"q",antedecent[3]+"q"};
                antedecent = harmony.findClosestInversion(antedecent, chordProgression.get(i));
//             patternString += antedecent[0] + "w+" + antedecent[1] + "w+" + antedecent[2] + "w+" + antedecent[3] + "w ";
            arpeggios.arpegiate(arpegi,2);
            }
//
            String toplay = arpeggios.getPatternString();
            patternString += toplay;
            patternString += "V1 ";
            Melody melody = new Melody(a);
            for(int i = 0; i < 60; i++) {
                melody.addRandomMelody(patternString, null, strInput);
                strInput = melody.getStrInput();
                patternString=melody.getPatternString();
            }



            Pattern pattern = new Pattern(patternString);

            System.out.println(patternString);
            harmony.decodeFunctionalHarmony(chordProgression);
            pattern.add(patternString);
//
            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));


//.......................................................................................
//            Melody melody = new Melody(a);
//            // add 4 note random motif
//            String patternString = "V0 ";
//            for(int i = 0; i < 12; i++) {
//                melody.addRandomMelody(patternString, null, strInput);
//                strInput = melody.getStrInput();
//                patternString=melody.getPatternString();
//            }
//            String motiv1 = patternString;
//            Motif motif = new Motif(patternString, patternString, a, strInput);
//            motif.developPattern();
//            patternString= motif.getPatternString();
//
//            Pattern pattern = new Pattern();
//            pattern.add(patternString);
////
//            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
//            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
//            System.out.println(mainPattern.toString());
//            String decodedSong =  midiUtils.parseIncomingNotes(mainPattern.toString());
//            System.out.println(decodedSong);
//            Motif decodeMotif = new Motif(motiv1,a,"",decodedSong);
//            decodeMotif.decodePattern();
//

//..............................................................................................................

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

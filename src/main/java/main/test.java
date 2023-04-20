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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class test {



    public static void main(String[] args) {
        try {
            MidiUtils midiUtils = new MidiUtils();

                String randomBinaryString = "";
                Random random = new Random();

                for (int i = 0; i < 1000; i++) {
                    int bit = random.nextInt(2);
                    randomBinaryString += bit;
                }


                String strInput = randomBinaryString;
//                Conductor conductor = new Conductor(strInput);
//                Pattern pattern = new Pattern(conductor.composeSong());
//
//            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
//            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
//            System.out.println(mainPattern.toString());
////            Analyst analyst = new Analyst(midiUtils.parseIncomingNotes(mainPattern.toString()));
////            analyst.analyzeSong();




//            String strInput = "01011000100110101101001001100101110" +
//                    "01101100110011110010010001100100" +
//                    "01111001101010011100011000011000" +
//                    "11000001010010011110100101000000" +
//                    "01101100110011110010010001100100" +
//                    "01111001101010011100011000011000" +
//                    "11000001010010011110100101000000" +
//                    "01101100110011110010010001100100" +
//                    "01111001101010011100011000011000" +
//                    "11000001010010011110100101000000" +
//                    "10110101001011100001010101000100";
//
           Key key = new Key(strInput);

//            for (int i = 0; i < 32; i++){
//                Integer[] a = key.generateKeyTest(i);
//                Harmony harmony = new Harmony(a,strInput);
//                harmony.printChords();
//                System.out.println();
//            }
//
            Integer[] a  = key.generateKey();
            strInput = key.getStrInput();

            Harmony harmony = new Harmony(a,strInput);

            harmony.createFunctionalHarmony(10);
            strInput = harmony.getStrInput();
            List<Integer> chordProgression = harmony.getChordProgression();
            List<Integer[]> chords = harmony.getChords();

//            Pattern pattern = new Pattern("");
//            Rhytm rhytm = new Rhytm(strInput,a);
//            String[] rhytmisisedMelody = rhytm.createRhytmicisedMelody(8,2,chords,chordProgression,2,new Instrument("Piano"));
//            String mainPatern = String.join("",rhytmisisedMelody);
//
//            System.out.println("random melody"  + mainPatern);
//
//            Motif motif = new Motif(mainPatern,mainPatern,a,strInput);
//            motif.setExpandChord(chords.get(chordProgression.get(0)));
//            motif.developPattern();
//            mainPatern = motif.getPatternString();
//            pattern.add("T[Adagio] " + mainPatern);



            // ***************** harmony test   ******************

//            String bass = "V2 I[ELECTRIC_BASS_PICK] Rw Rw Rw ";
//            String patternString = "V0 ";
////            patternString += chords.get(0)[0] + "w+" + chords.get(0)[1] + "w+" + chords.get(0)[2] + "w+" + chords.get(0)[3] + "w ";
//            Integer[] antedecent = new Integer[]{chords.get(0)[0],chords.get(0)[1],chords.get(0)[2],chords.get(0)[3]};
//            Arpeggios arpeggios = new Arpeggios(patternString);
//
//            bass += harmony.createBassline(3);
//            for(int i = 1; i < chordProgression.size(); i++){
////                String arpegi[] = new String[]{antedecent[0]+"s",antedecent[1]+"s",antedecent[2]+"s",antedecent[3]+"s"};
//
////                antedecent = harmony.findClosestInversion(antedecent, chordProgression.get(i));
////                patternString += antedecent[0] + "w+" + antedecent[1] + "w+" + antedecent[2] + "w+" + antedecent[3] + "w ";
//
//
//            String arpegi[] = new String[]{ chords.get(chordProgression.get(i))[0] + "s",
//                    chords.get(chordProgression.get(i))[1] + "s",
//                    chords.get(chordProgression.get(i))[2] + "s",
//                    chords.get(chordProgression.get(i))[3] + "s"
//            };
//
//            arpeggios.arpegiateUpDown(arpegi,8);
//            }
////
//            String toplay = arpeggios.getPatternString();
//            patternString += toplay;
//

            Pattern pattern = new Pattern(
//                    " "+
//                    patternString +
//              bass
            );


            String previous = strInput;
            Drums drums = new Drums(strInput);
            Rhythm rhythm1 = drums.generateDrums(8,2); // 32 0, 16 1, 8 2
            Rhythm rhythm2 = drums.generateDrums(16,1);
            Rhythm rhythm3 = drums.generateDrums(32,0); // 32 0, 16 1, 8 2
            Rhythm r =  drums.concatRhytm(rhythm1,rhythm2);
            Rhythm r2 = drums.concatRhytm(r,rhythm3);


            strInput = drums.getStrInput();
//            pattern.add(rhythm.getPattern().repeat(3));
            pattern.add(r2);


            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
            System.out.println(mainPattern.toString());
            List<ArrayList<String>> voices = midiUtils.parseIncomingNotes(mainPattern.toString());

            for (ArrayList<String> voice : voices){
                System.out.println(voice);
            }
            System.out.println(previous);

            drums.parseDrums(midiUtils.getUnparsedDrums());
            String strOutput = drums.getStroutput();
            midiUtils.evaluateDeciphering(previous,strOutput);

//            System.out.println( harmony.decodeBassline(voices.get(0),3));

//-------------------------------------------------------------------------------------------------------------------



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




        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

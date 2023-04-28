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
//            MidiUtils midiUtils = new MidiUtils();
//
                String randomBinaryString = "";
                Random random = new Random();

                for (int i = 0; i < 2000; i++) {
                    int bit = random.nextInt(2);
                    randomBinaryString += bit;
                }

                String previous = randomBinaryString;
                String strInput = randomBinaryString;
//                Conductor conductor = new Conductor(strInput);
//                Pattern pattern = conductor.composeSong();
//
//
//            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
//            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
//            System.out.println(mainPattern.toString());
//            Analyst analyst = new Analyst(midiUtils.parseIncomingNotes(mainPattern.toString()));
//            String returnedString = analyst.analyzeSong(midiUtils);
//            midiUtils.evaluateDeciphering(previous,returnedString);






           Key key = new Key(strInput);


            Integer[] a  = key.generateKey();
            strInput = key.getStrInput();

            Harmony harmony = new Harmony(a,strInput);

            harmony.createFunctionalHarmony(10);
            strInput = harmony.getStrInput();
            List<Integer> chordProgression = harmony.getChordProgression();
            List<Integer[]> chords = harmony.getChords();

            Pattern pattern = new Pattern("");
            Rhytm rhytm = new Rhytm(strInput,a);


            String[] rhytmisisedMelody = rhytm.createRhytmicisedMelody(4,1,chords,chordProgression,2,new Instrument("Piano"));
            String mainPatern = String.join("",rhytmisisedMelody);

            System.out.println("random melody"  + mainPatern);

//            Motif motif = new Motif(mainPatern,mainPatern,a,strInput);
//            motif.setExpandChord(chords.get(chordProgression.get(0)));
//            motif.developPattern();
//            mainPatern = motif.getPatternString();
//            pattern.add(mainPatern);



            MidiFileManager.savePatternToMidi(pattern, new File("miusik.mid"));
            Pattern mainPattern = MidiFileManager.loadPatternFromMidi(new File("miusik.mid"));
            System.out.println(mainPattern.toString());
//            List<ArrayList<String>> voices = midiUtils.parseIncomingNotes(mainPattern.toString());
//
//            for (ArrayList<String> voice : voices){
//                System.out.println(voice);
//            }
//            System.out.println(previous);
//
//            drums.parseDrums(midiUtils.getUnparsedDrums());
//            String strOutput = drums.getStroutput();
//            midiUtils.evaluateDeciphering(previous,strOutput);

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

package main.composition;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

import java.util.List;

public class Conductor {
    String strInput;
    Pattern pattern = new Pattern();
    String[] voices = new String[10];
    Harmony harmony;
    Key key;
    //Voices
    //V0 Harmonic acompaniment
    //V1 main melody
    //V2 Bass
    //V3 secondary melody
    //v9 drums
    public Conductor(String s) {
        strInput=s;
        for(int i = 0; i < voices.length; i++){
            voices[i] = "V"+i + " ";
        }
    }

    public Pattern composeSong() throws Exception {

        drums();
        harmony();



        Pattern lastPattern = new Pattern();
        lastPattern.add(pattern);
        lastPattern.add(connectAllVoices());
        return lastPattern;
    }

    private void harmony() throws Exception{
        key = new Key(strInput);
        Integer[] a  = key.generateKey();
        strInput = key.getStrInput();
//        encodeKey(key,a);
        harmony = new Harmony(a,strInput);
        harmony.createFunctionalHarmony(11);
        List<Integer> chordProgression = harmony.getChordProgression();
        List<Integer[]> chords = harmony.getChords();
        strInput = harmony.getStrInput();
        this.voices[3] += " I74 ";
        this.voices[2] += " I34 ";
        for (int i = 0; i < chordProgression.size(); i++){
            this.voices[3] += (chords.get(chordProgression.get(i))[0]+24) + "h ";
        }
        for( int i = 0; i < voices.length;i++){
            voices[i] += "Rh ".repeat(chordProgression.size());
        }
        encodeKey(key,a);
                    Arpeggios arpeggios = new Arpeggios(voices[0]);

        for(int i = 0; i < chordProgression.size(); i++){
//                String arpegi[] = new String[]{antedecent[0]+"s",antedecent[1]+"s",antedecent[2]+"s",antedecent[3]+"s"};

//                antedecent = harmony.findClosestInversion(antedecent, chordProgression.get(i));
//                patternString += antedecent[0] + "w+" + antedecent[1] + "w+" + antedecent[2] + "w+" + antedecent[3] + "w ";

            String arpegi[] = new String[]{ chords.get(chordProgression.get(i))[0] + "s",
                    chords.get(chordProgression.get(i))[1] + "s",
                    chords.get(chordProgression.get(i))[2] + "s",
                    chords.get(chordProgression.get(i))[3] + "s"
            };

            arpeggios.arpegiateRandom(arpegi,8);
            }
            this.voices[0]=arpeggios.getPatternString();
            this.voices[2] += harmony.createBassline(2);
    }

    private void encodeKey( Key key,Integer[] a){
        int whatKey = key.getWhatKey();
        System.out.println("Key was: " + whatKey +" : " + a[0]);

        switch (whatKey) {
            case 0 -> this.voices[1] += a[35] + "i Ri";
            case 1 -> this.voices[1] += a[35] + "i " + a[36] + "i " + "Ri";
            case 2 -> this.voices[1] += a[35] + "i " + a[34] + "i " + "Ri";
            case 3 -> this.voices[1] += a[35] + "i " + a[35] + "i " + "Ri";
            default -> {
            }
        }
//        this.voices[1] +=  " 43i";
    }

    private String connectAllVoices(){
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < voices.length; i++){
            if(i!=9)
                a.append(voices[i]).append(" ");
        }
        return  a.toString();
    }

    private void drums(){
        String previous = strInput;
        Drums drums = new Drums(strInput);
        Rhythm rhythm1 = drums.generateDrums(8,2); // 32 0, 16 1, 8 2
        Rhythm rhythm2 = drums.generateDrums(16,1);
        Rhythm rhythm3 = drums.generateDrums(32,0); // 32 0, 16 1, 8 2
        Rhythm r =  drums.concatRhytm(rhythm1,rhythm2);
        Rhythm r2 = drums.concatRhytm(r,rhythm3);
        strInput = drums.getStrInput();
        pattern.add(r2);

    }


    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }
}

package main.composition;

import main.utils.MidiUtils;
import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;

import java.util.List;

public class Conductor {
    String strInput;
    Pattern pattern = new Pattern();
    String[] voices = new String[10];
    Harmony harmony;
    Key key;
    Integer[] a;
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
        melody();


        Pattern lastPattern = new Pattern();
        lastPattern.add("T[Moderato] "+ pattern);
        lastPattern.add(connectAllVoices());
        System.out.println(" velkost zasif " + this.strInput.length());
        return lastPattern;
    }


    private void melody(){ //voices 4
        this.voices[4] += " Rw Rw Rw Rw ";


        Melody melody = new Melody(a);
        melody.setStrInput(strInput);
        Rhytm rhytm = new Rhytm(strInput,a);

        String[] rhytmisisedMelody = rhytm.createRhytmicisedMelody(4,1,harmony.getChords(),harmony.getChordProgression(),2,new Instrument("Violin"),2);
        String mainPatern = String.join("",rhytmisisedMelody);

        this.voices[4] += mainPatern;
        strInput= melody.getStrInput();

        Motif motif = new Motif(mainPatern, this.voices[4], this.a, this.strInput);
            motif.developPattern0();
            this.voices[4] = motif.getPatternString();


        rhytmisisedMelody = rhytm.createRhytmicisedMelody(4,1,harmony.getChords(),harmony.getChordProgression(),2,new Instrument("Violin"),2);
        mainPatern = String.join("",rhytmisisedMelody);

        this.voices[4] += mainPatern;
        strInput= melody.getStrInput();

        motif = new Motif(mainPatern, this.voices[4], this.a, this.strInput);
        motif.developPattern1();
        this.voices[4] = motif.getPatternString();


    }

    private void harmony() throws Exception{
        key = new Key(strInput);
        a  = key.generateKey();
        strInput = key.getStrInput();
//        encodeKey(key,a);
        harmony = new Harmony(a,strInput);
        harmony.createFunctionalHarmony(11);
        List<Integer> chordProgression = harmony.getChordProgression();
        List<Integer[]> chords = harmony.getChords();
        strInput = harmony.getStrInput();
        this.voices[3] += " I74 ";
        this.voices[2] += " I34 ";
        this.voices[4] += " I40 ";
        for (int i = 0; i < chordProgression.size(); i++){
            this.voices[3] += (chords.get(chordProgression.get(i))[0]+24) + "h ";
        }
        for( int i = 0; i < voices.length;i++){
            voices[i] += "Rh ".repeat(chordProgression.size());
        }
        encodeKey(key,a);
        this.voices[2] += "Rw Rw Rw Rw " + harmony.createBassline(2);
        strInput = harmony.getStrInput();
        Arpeggios arpeggios = new Arpeggios(voices[0]);
        arpeggios.setStrInput(strInput);
        for(int i = 0; i < chordProgression.size(); i++){
//                String arpegi[] = new String[]{antedecent[0]+"s",antedecent[1]+"s",antedecent[2]+"s",antedecent[3]+"s"};

//                antedecent = harmony.findClosestInversion(antedecent, chordProgression.get(i));
//                patternString += antedecent[0] + "w+" + antedecent[1] + "w+" + antedecent[2] + "w+" + antedecent[3] + "w ";

            String arpegi[] = new String[]{ chords.get(chordProgression.get(i))[0] + "s",
                    chords.get(chordProgression.get(i))[1] + "s",
                    chords.get(chordProgression.get(i))[2] + "s",
                    chords.get(chordProgression.get(i))[3] + "s"
            };

            arpeggios.arpegiateRandomInput(arpegi,4);
            strInput = arpeggios.getStrInput();
            }
            this.voices[0]=arpeggios.getPatternString();

    }

    private void encodeKey( Key key,Integer[] a){
        int whatKey = key.getWhatKey();
        System.out.println("Key was: " + whatKey +" : " + a[0]);

        switch (whatKey) {
            case 0 -> this.voices[1] += a[35] + "i Ri ";
            case 1 -> this.voices[1] += a[35] + "i " + a[36] + "i " + "Ri ";
            case 2 -> this.voices[1] += a[35] + "i " + a[34] + "i " + "Ri ";
            case 3 -> this.voices[1] += a[35] + "i " + a[35] + "i " + "Ri ";
            default -> {
            }
        }
        this.voices[1] +=  a[35] + "i ";
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
        Rhythm rhythm1 = drums.generateDrums(8,2,1); // 32 0, 16 1, 8 2
        Rhythm rhythm2 = drums.generateDrums(16,1,0);
        Rhythm rhythm3 = drums.generateDrums(32,0,0); // 32 0, 16 1, 8 2
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

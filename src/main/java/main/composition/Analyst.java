package main.composition;

import main.utils.MidiUtils;

import java.util.*;

public class Analyst {

    List<ArrayList<String>> parsedSong;
    String strOutput = "";
    Key key;
    Integer [] a;
    MidiUtils midiUtils;
    Harmony harmony;

    Map<String, Integer> lenghthsMap;
    public Analyst(List<ArrayList<String>> parsedSong) {
        this.parsedSong = parsedSong;
        lenghthsMap = new HashMap<>();
        lenghthsMap.put("q", 4);
        lenghthsMap.put("i", 2);
        lenghthsMap.put("i.", 3);
        lenghthsMap.put("s", 1);


    }


    public String analyzeSong(MidiUtils midiUtils) throws Exception {
        this.midiUtils = midiUtils;
        decomposeDrums();
        decomposeKey();
        decomposeHarmony();
//        decomposeMelody();
        return strOutput;
    }

    private void decomposeMelody(){
        ArrayList<String> melody1 = divideMelody(1);
        ArrayList<String> melody2 = divideMelody(2);
        ArrayList<String> melody3 = divideMelody(3);
        ArrayList<String> melody4 = divideMelody(4);
        ArrayList<String> melody5 = divideMelody(5);
        ArrayList<String> melody6 = divideMelody(6);
        strOutput += this.decodeMelody1(melody1);


//        System.out.println();



    }



    private String decodeMelody1(ArrayList<String> melody){
        String result = "";
        Rhytm rhytm = new Rhytm("",a);
        ArrayList<String> m1 = new ArrayList<>();
        int length= 0;
        int i = 0;
        while(length < 16){
            m1.add(melody.get(i));
            i++;

            length = length + lenghthsMap.get(melody.get(i).substring(2,melody.get(i).length()-1));
        }
        result += rhytm.decodeRhytmicisedMelody(4,1,harmony.getChords().get(harmony.getChordProgression().get(2)),m1);

        return result;
    }
    private String decodeMelody2(ArrayList<String> melody){
        String result = "";

        return result;
    }
    private String decodeMelody3(ArrayList<String> melody){
        String result = "";

        return result;
    }
    private String decodeMelody4(ArrayList<String> melody){
        String result = "";

        return result;
    }
    private String decodeMelody5(ArrayList<String> melody){
        String result = "";

        return result;
    }
    private String decodeMelody6(ArrayList<String> melody){
        String result = "";

        return result;
    }



    private ArrayList<String> divideMelody(int a)
    {
        ArrayList<String> result  =new ArrayList<>();

        switch (a){
            case 1:
                //violin
                for (int i = 2; i < parsedSong.get(5).size(); i++){
                    if(parsedSong.get(5).get(i).startsWith("R"))
                        return result;
                    else
                        result.add(parsedSong.get(5).get(i));
                }
                parsedSong.get(5).remove(1);
                break;
                //guitar
            case 2:
                for (int i = 2; i < parsedSong.get(6).size(); i++){
                    if(parsedSong.get(6).get(i).startsWith("R"))
                        return result;
                    else
                        result.add(parsedSong.get(6).get(i));
                }
                parsedSong.get(6).remove(1);

                break;
                //sax
            case 3:
                for (int i = 2; i < parsedSong.get(7).size(); i++){
                    if(parsedSong.get(7).get(i).startsWith("R"))
                        return result;
                    else
                        result.add(parsedSong.get(7).get(i));
                }
                parsedSong.get(7).remove(1);

                break;
                //quitar
            case 4:
                int b = 0;
                for (int i = 2; i < parsedSong.get(6).size(); i++){
                    if(parsedSong.get(6).get(i).startsWith("R"))
                        b=i;
                }
                for (int i = b+1; i < parsedSong.get(6).size(); i++){
                    result.add(parsedSong.get(6).get(i));
                }
                return result;

            case 5: //vilin
                 b = 0;
                for (int i = 2; i < parsedSong.get(5).size(); i++){
                    if(parsedSong.get(5).get(i).startsWith("R"))
                        b=i;
                }
                for (int i = b+1; i < parsedSong.get(5).size(); i++){
                    result.add(parsedSong.get(5).get(i));
                }
                return result;

            case 6: //sax

                 b = 0;
                for (int i = 2; i < parsedSong.get(7).size(); i++){
                    if(parsedSong.get(7).get(i).startsWith("R"))
                        b=i;
                }
                for (int i = b+1; i < parsedSong.get(7).size(); i++){
                    result.add(parsedSong.get(7).get(i));
                }
                return result;

            default:
                break;
        }

        return result;

    }

    public void decomposeKey(){
  ArrayList<String> firstMelody = parsedSong.get(2);
  firstMelody.remove(firstMelody.get(0));
        key = new Key("");
        if(firstMelody.get(1).startsWith("R")){
            int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
            a = key.generateKeyTest( choice );
            strOutput+=getChoiceString(choice,5);
//            System.out.println(strOutput);
        }else {
            int a = Integer.parseInt(firstMelody.get(0),0,2,10);
            int b = Integer.parseInt(firstMelody.get(1),0,2,10);
            if(b > a){
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                this.a = key.generateKeyTest( choice + 12 );
                strOutput+=getChoiceString(choice+ 12,5);
//                System.out.println(strOutput);
            }else if(a > b){
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                this.a = key.generateKeyTest( choice +24-1 );
                strOutput+=getChoiceString(choice+24-1,5);
//                System.out.println(strOutput);
            }else {
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                this.a = key.generateKeyTest( choice + 29 );
                strOutput+=getChoiceString(choice+ 29,5);
//                System.out.println(strOutput);
            }
        }
    }

    private void decomposeDrums(){
        Drums drums = new Drums("");
        drums.parseDrums(this.midiUtils.getUnparsedDrums());
        this.strOutput = drums.getStroutput();
    }

    private void decomposeHarmony() throws Exception{
        harmony = new Harmony(a,"");
        List<Integer> chordProgression = getChordProgression(harmony);
        harmony.setChordProgression(chordProgression);
        harmony.decodeFunctionalHarmony(chordProgression);
        this.strOutput += harmony.getStrOutput();
        this.strOutput += decodeBass(harmony);
        this.strOutput += decodeHarmonicSupport(harmony,4);
    }

    private String decodeHarmonicSupport(Harmony harmony,int length){
        String result = "";
        Arpeggios arpeggios = new Arpeggios("");
        for (int i = 0; i < 43; i++){
            this.parsedSong.get(1).remove(0);
        }


        ArrayList<String> harmonicSupport = parsedSong.get(1);
//        harmonicSupport.remove(0);


//        String[] objects = harmonicSupport.toArray();
        int harmonicIndex=0;
        String[] notes = new String[4];
        for (int i = 0; i < harmony.getChordProgression().size(); i++){
              Integer[] chord = Arrays.copyOfRange(harmony.getChords().get(harmony.getChordProgression().get(i)),
                                      0, 4);


              for(int j = 0; j < length; j++){
                  notes[0]= harmonicSupport.get(harmonicIndex).substring(0,2);
                  notes[1]= harmonicSupport.get(harmonicIndex + 1).substring(0,2);
                  notes[2]= harmonicSupport.get(harmonicIndex + 2).substring(0,2);
                  notes[3]= harmonicSupport.get(harmonicIndex + 3).substring(0,2);
                  result += arpeggios.decodeRandomInput(notes,chord);
                  harmonicIndex = harmonicIndex + 8;
              }
        }
        return result;
    }

    private String decodeBass(Harmony harmony){
//        System.out.println();
        return harmony.decodeBassline(parsedSong.get(3),2);
    }

    private List<Integer> getChordProgression(Harmony harmony){
        List<Integer> chordProgression = new ArrayList<>();
        List<Integer[]> chords = harmony.getChords();
        ArrayList<String> flutePart = this.parsedSong.get(4);
        flutePart.remove(flutePart.get(0));

        for(int i = 0; i < 16; i++){
            for (int j = 0; j < chords.size(); j++){
                if(Integer.parseInt(flutePart.get(i).substring(0,2)) == (chords.get(j)[0] + 24))
                    chordProgression.add(j);
            }
        }
        return chordProgression;
    }

    public void removeR(int i){
        for (int j = 0; j < this.parsedSong.get(i).size(); j++ ){
            if ( this.parsedSong.get(i).get(j).startsWith("R")){
                this.parsedSong.get(i).remove(j);
                j--;
            }
        }
    }



    private String getChoiceString(int choice, int length){
        String s= Integer.toBinaryString(choice);
        while (s.length()<length){
            s = "0"+s;
        }
        return s;
    }

}

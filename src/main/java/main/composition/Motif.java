package main.composition;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Motif {


    //todo Present - Repeat - Vary - Deminish
    //todo reasoner which decides which motivic

    Map<String, Double> lenghtsMap = new HashMap<String, Double>() {{
        put("q",4.0);
        put("i.",3.0);
        put("i",2.0);
        put("s.",1.5);
        put("s",1.0);
        put("t",0.5);
    }};


    String mainMotif;
    //half
    String[] possibleLengths = new String[] {
            "h", "q.", "q", "i.", "i", "s.", "s", "t.","t" };
//    Pair[] parsedMotif;
    List<Pair<Integer, String>> parsedMotif = new ArrayList<>();

    List<Pair<Integer, String>> songToDecode = new ArrayList<>();
    String strInput;

    public String getStrOutput() {
        return strOutput;
    }

    public void setStrOutput(String strOutput) {
        this.strOutput = strOutput;
    }
    Integer[] expandChord;
    String strOutput;
    Integer[] key;

    String patternString;
    //encoding constructor
    public Motif(String mainMotif, String patternString, Integer[] key, String strInput) {
        this.key=key;
        this.mainMotif = mainMotif;
        this.patternString = patternString;
        this.strInput = strInput;
        this.parsedMotif = parseMotif(mainMotif);
    }
    //decoding constructor
    public Motif(String mainMotif, Integer[] key, String strOutput,String song){
        this.key = key;
        this.mainMotif=mainMotif;
        this.strOutput = strOutput;
        this.songToDecode = parseMotif(song);
        this.parsedMotif = parseMotif(mainMotif);
    }

    public String getPatternString() {
        return patternString;
    }

    public void setPatternString(String patternString) {
        this.patternString = patternString;
    }




    public Integer[] getKey() {
        return key;
    }

    public void setKey(Integer[] key) {
        this.key = key;
    }

    public String getMainMotif() {
        return mainMotif;
    }

    public void setMainMotif(String mainMotif) {
        this.mainMotif = mainMotif;
    }

    public String getStrInput() {
        return strInput;
    }

    public void setStrInput(String strInput) {
        this.strInput = strInput;
    }

    public String createAnswer(){
        return null;
    }

    public String decodeAnswer(){
        return null;
    }

    public String createQuestion(){
        return null;
    }

    public String decodeQuestion(){
        return null;
    }

    public void createSmallMotif(){

    }

    private List<Pair<Integer, String>> parseMotif(String motifToParse){
        List<Pair<Integer, String>> parsed = new ArrayList<>();
        String[] tokenised = motifToParse.split(" ");
        String length = "";
        Integer pitch;
        for(String s : tokenised){
            if(s.startsWith("V")||s.startsWith("I")||s.startsWith("T")||s.startsWith("R"))
                continue;
            if(s.endsWith(".")){
                length = s.substring(s.length() - 2);
                pitch = Integer.parseInt(s.substring(0,s.length()-2));
            }else {
                length = s.substring(s.length() - 1);
                pitch = Integer.parseInt(s.substring(0,s.length()-1));
            }

            parsed.add(new Pair<>(pitch,length));
        }
        return parsed;
    }


    //sequence = changing pitches in melody, for example for using same motif in different chord
    // how do i encode shit in sequence, relative distance is the key-.

    public void createSequence(int relativeDistance){
        StringBuilder resultMotif = new StringBuilder();
        int choice = getChoice(1);
        if(choice==0){
            relativeDistance = relativeDistance*(-1);
        }

        for( int i = 0; i < parsedMotif.size(); i++){

            int notePos= this.getPositionInKey(parsedMotif.get(i).getValue0());

            resultMotif.append( key[notePos+ relativeDistance] );
            resultMotif.append(parsedMotif.get(i).getValue1()).append(" ");

        }
        this.patternString += resultMotif.toString();
    }

    private int getPositionInKey(Integer note){
        for(int i = 0; i < key.length; i++){
            if(note.equals(key[i])){
                return i;
            }
        }

        return -1;
    }

    public String decodeSequence(){
        return null;
    }

    //augment = add longer lenghts
    // if some length is not greater than the biggest length 
//    String[] possibleLengths = new String[] {
//            "h", "q.", "q", "i.", "i", "s.", "s", "t.","t" };

    public void createAugment(){
        for (Pair p : parsedMotif){
            switch (p.getValue1().toString()){
                case "h ":
                    break;
                case "q. ":
                    break;
                case "q ":
                    int choice = getChoice(1);
                    if(choice==0){
                        this.patternString += p.getValue0() + "q. ";
                    }else {
                        this.patternString += p.getValue0() + "h ";
                    }
                    break;
                case "i.":
                    choice = getChoice(1);
                    if(choice==0){
                        this.patternString += p.getValue0() + "q ";
                    }else {
                        this.patternString += p.getValue0() + "q. ";
                    }
                    break;
                case "i":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "q ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "q. ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "h ";
                            break;
                    }
                    break;
                case "s.":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "q ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "q. ";
                            break;
                    }
                    break;
                case "s":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "q ";
                            break;
                    }
                    break;
                case "t.":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "s ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                    }
                    break;
                case "t":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "t. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "s ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 4:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 5:
                            this.patternString += p.getValue0() + "q ";
                            break;
                        case 6:
                            this.patternString += p.getValue0() + "q. ";
                            break;
                        case 7:
                            this.patternString += p.getValue0() + "h ";
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public String decodeAugment(){
        return null;
    }

    // dimunation = lesser lengths

    public void createDimunation(){
        for (Pair p : parsedMotif){
            switch (p.getValue1().toString()){
                case "t ":
                    break;
                case "t. ":
                    break;
                case "s ":
                    break;
                case "s.":
                    break;
                case "i":
                    int choice = getChoice(1);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "s ";
                            break;
                    }
                    break;
                case "i.":
                    choice = getChoice(1);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "s ";
                            break;
                    }
                    break;
                case "q":
                    choice = getChoice(1);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "s ";
                            break;
                    }
                    break;
                case "q.":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "q ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "i ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                    }
                    break;
                case "h":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "q. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "q ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "i. ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "i ";
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public String decodeDimunation(){
        return null;
    }

    // expand = add more notes
    // from input string 010110 of motif length will be taken. If 1 is then note will be added after if 0 then no.
    // if 1 is then new note will be added based on new input taken
    //lets try to make 8 chord notes
    public void createExpand(Integer[] chordNotes){
        StringBuilder stringBuilder = new StringBuilder(patternString);

        for (Pair p : parsedMotif){
            int choice = getChoice(1);
            if(choice==0){
                stringBuilder.append(p.getValue0()).append(p.getValue1()).append(" ");
//                stringBuilder.append( "R").append(p.getValue1()).append(" ");

            }
            else {
                //here i should add something that sounds good, so what about chord note?
                choice=getChoice(2);
                stringBuilder.append(p.getValue0()).append(p.getValue1()).append(" ");
                stringBuilder.append(chordNotes[choice]).append(p.getValue1()).append(" ");
            }
        }
        patternString = stringBuilder.toString();
    }

    public String decodeExpand(){
        return null;
    }



    // contract = reduce some notes
    //how to get some bits into deleting notes?
    // if 0 delete note from motif, if 1 keep it
    public void createContract(){
        int choice;
        StringBuilder stringBuilder = new StringBuilder(patternString);
        for (Pair p : parsedMotif){
            choice= this.getChoice(1);
            if(choice==0){
                stringBuilder.append(p.getValue0()).append(p.getValue1()).append(" ");
            }
            // else is negligible since nothing happens, but in decoding should be addressed
        }
        patternString = stringBuilder.toString();

    }

    public void decodeContract(  List<Pair<Integer, String>> melodyToDecode, int startIndex){
        StringBuilder stringBuilder = new StringBuilder(strOutput);
        for (Pair p : parsedMotif){
            if(startIndex>=melodyToDecode.size())
                break;
            if(melodyToDecode.get(startIndex).equals(p)){
                stringBuilder.append("0");
                startIndex++;
            }else {
                stringBuilder.append("1");
            }
        }
        this.strOutput = stringBuilder.toString();
    }

    // divide = segment notes to AB and then make AABB
    //if i can devide motif to abc then we can make abc
    // smallest part of devision should be
    public void createDivide(){
        int divisorLength = parsedMotif.size() / 2;
        StringBuilder stringBuilder = new StringBuilder(patternString);
        List<Pair<Integer, String>> firstPart = parsedMotif.subList(0,divisorLength);
        List<Pair<Integer, String>> secondPart = parsedMotif.subList(divisorLength,parsedMotif.size());
        int choice = getChoice(2);
        switch (choice){
            case 0: //aabb
                stringBuilder.append(appendAll(firstPart))
                        .append(appendAll(firstPart))
                        .append(appendAll(secondPart))
                        .append(appendAll(secondPart));
                break;
            case 1: //abab
                stringBuilder.append(appendAll(firstPart))
                        .append(appendAll(secondPart))
                        .append(appendAll(firstPart))
                        .append(appendAll(secondPart));
                break;
            case 2: //bbaa
                stringBuilder.append(appendAll(secondPart))
                        .append(appendAll(secondPart))
                        .append(appendAll(firstPart))
                        .append(appendAll(firstPart));
                break;
            case 3: //baba
                stringBuilder.append(appendAll(secondPart))
                        .append(appendAll(firstPart))
                        .append(appendAll(secondPart))
                        .append(appendAll(firstPart));
                break;
        }
        this.patternString = stringBuilder.toString();
    }

    private String appendAll(List<Pair<Integer, String>> motif){
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair p : motif){
            stringBuilder.append(p.getValue0()).append(p.getValue1()).append(" ");
        }
        return stringBuilder.toString();
    }

    public String decodeDivide(){
        return null;
    }

    //q = 4. i = 2. s = i.
    public void deconstructMotif(int end){
        double beatCounter=0;
        int motifCounter= 0;
        StringBuilder stringBuilder = new StringBuilder("");
        while(beatCounter < 6.0){
            beatCounter =  beatCounter + lenghtsMap.get(parsedMotif.get(motifCounter).getValue1());
            patternString += parsedMotif.get(motifCounter).getValue0() + parsedMotif.get(motifCounter).getValue1() + " ";
            motifCounter++;
        }

        int lastPos = this.getPositionInKey(parsedMotif.get(motifCounter-1).getValue0());
        if(end == 1){
            if(lastPos >35){
                patternString+= key[lastPos -1] + "i ";
                patternString+= key[lastPos -2] + "i ";
                patternString+= key[35] + "q ";
            }else {
                patternString+= key[lastPos +1] + "i ";
                patternString+= key[lastPos +2] + "i ";
                patternString+= key[35] + "q ";
            }

        }else {

            if(lastPos >41){
                patternString+= key[lastPos -1] + "i ";
                patternString+= key[lastPos -2] + "i ";
                patternString+= key[41] + "q ";
            }else {
                patternString+= key[lastPos +1] + "i ";
                patternString+= key[lastPos +2] + "i ";
                patternString+= key[41] + "q ";
            }
        }

    }

    public void developPattern(){


        this.createSequence(1);
//        this.createSequence(2);
        this.createAugment();
        this.deconstructMotif(1);


//
//           this.createDivide();
this.createContract();
//        this.createExpand(expandChord);
//        this.createDimunation();
//        this.createAugment();
//        this.deconstructMotif(1);

//        this.createExpand(expandChord);

    }

    public void decodePattern(){

        this.decodeContract(songToDecode,2);
    }

    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }

    public Integer[] getExpandChord() {
        return expandChord;
    }

    public void setExpandChord(Integer[] expandChord) {
        this.expandChord = expandChord;
    }
}

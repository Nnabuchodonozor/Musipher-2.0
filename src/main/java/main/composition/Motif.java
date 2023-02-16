package main.composition;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Motif {


    //todo Present - Repeat - Vary - Deminish
    //todo reasoner which decides which motivic
    String mainMotif;
    String[] possibleLengths = new String[] {
            "h", "q.", "q", "i.", "i", "s.", "s", "t.","t" };
//    Pair[] parsedMotif;
    List<Pair<Integer, String>> parsedMotif = new ArrayList<>();
    String strInput;
    Integer[] key;

    String patternString;
    public Motif(String mainMotif, String patternString, Integer[] key) {
        this.key=key;
        this.mainMotif = mainMotif;
        this.patternString = patternString;
        this.parseMotif();
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

    private void parseMotif(){
        String[] tokenised = this.mainMotif.split(" ");
        for(String s : tokenised){
            if(s.startsWith("V")||s.startsWith("I"))
                continue;
            String length = s.substring(s.length() - 1);
            Integer pitch = Integer.parseInt(s.substring(0,s.length()-1));
            this.parsedMotif.add(new Pair<>(pitch,length));
        }
    }


    //sequence = changing pitches in melody, for example for using same motif in different chord
    //

    public String createSequence(int relativeDistance){
        StringBuilder resultMotif = new StringBuilder();
        for( int i = 0; i < parsedMotif.size(); i++){
            int notePos= this.getPositionInKey(parsedMotif.get(i).getValue0());
            resultMotif.append( key[notePos] + relativeDistance);
            resultMotif.append(parsedMotif.get(i).getValue1()).append(" ");

        }
        return resultMotif.toString();
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

    public String createAugment(){
        return null;
    }

    public String decodeAugment(){
        return null;
    }

    // dimunation = lesser lengths

    public String createDimunation(){
        return null;
    }

    public String decodeDimunation(){
        return null;
    }

    // expand = add more notes

    public String createExpand(){
        return null;
    }

    public String decodeExpand(){
        return null;
    }
    // contract = reduce some notes

    public String createContract(){
        return null;
    }

    public String decodeContract(){
        return null;
    }
    // divide = segment notes to AB and then make AABB

    public String createDivide(){
        return null;
    }

    public String decodeDivide(){
        return null;
    }

    public void developPattern(){

        String motifToAdd = this.createSequence(3);
        this.patternString += motifToAdd;

        String motifToAdd1 = this.createSequence(4);
        this.patternString += motifToAdd1;

        String motifToAdd2 = this.createSequence(5);
        this.patternString += motifToAdd2;
//        for()
//        this.patternString +=


    }

}

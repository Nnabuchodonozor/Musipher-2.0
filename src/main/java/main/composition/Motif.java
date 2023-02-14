package main.composition;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Motif {


    // Present - Repeat - Vary - Deminish
    String mainMotif;
//    Pair[] parsedMotif;
    List<Pair<Integer, String>> parsedMotif = new ArrayList<>();
    String strInput;
    Integer[] key;

    String patternString;
    public Motif(String mainMotif, String patternString) {
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
        String resultMotif = "";
        for( int i = 0; i < parsedMotif.size(); i++){
            resultMotif += parsedMotif.get(i).getValue0() + relativeDistance;
            resultMotif += parsedMotif.get(i).getValue1() + " ";

        }
        return resultMotif;
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
        this.patternString += motifToAdd;

        String motifToAdd2 = this.createSequence(5);
        this.patternString += motifToAdd;
//        for()
//        this.patternString +=


    }

}

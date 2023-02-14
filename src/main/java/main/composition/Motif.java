package main.composition;

public class Motif {


    // Present - Repeat - Vary - Deminish
    Integer[] mainMotif;
    String strInput;
    Integer[] key;

    public Integer[] getKey() {
        return key;
    }

    public void setKey(Integer[] key) {
        this.key = key;
    }

    public Integer[] getMainMotif() {
        return mainMotif;
    }

    public void setMainMotif(Integer[] mainMotif) {
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


    //sequence = changing pitches in melody, for example for using same motif in different chord
    //

    public Integer[] createSequence(int relativeDistance){
        Integer[] resultMotif = new Integer[mainMotif.length];
        for( int i = 0; i < mainMotif.length; i++){
            resultMotif[i] = mainMotif[i] + relativeDistance;
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
}

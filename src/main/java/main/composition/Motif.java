package main.composition;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Motif {


    //todo Present - Repeat - Vary - Deminish
    //todo reasoner which decides which motivic
    String mainMotif;
    //half
    String[] possibleLengths = new String[] {
            "h", "q.", "q", "i.", "i", "s.", "s", "t.","t" };
//    Pair[] parsedMotif;
    List<Pair<Integer, String>> parsedMotif = new ArrayList<>();
    String strInput;

    public String getStrOutput() {
        return strOutput;
    }

    public void setStrOutput(String strOutput) {
        this.strOutput = strOutput;
    }

    String strOutput;
    Integer[] key;

    String patternString;
    public Motif(String mainMotif, String patternString, Integer[] key, String strInput) {
        this.key=key;
        this.mainMotif = mainMotif;
        this.patternString = patternString;
        this.strInput = strInput;
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
        String length = "";
        Integer pitch;
        for(String s : tokenised){
            if(s.startsWith("V")||s.startsWith("I"))
                continue;
            if(s.endsWith(".")){
                length = s.substring(s.length() - 2);
                pitch = Integer.parseInt(s.substring(0,s.length()-2));
            }else {
                length = s.substring(s.length() - 1);
                pitch = Integer.parseInt(s.substring(0,s.length()-1));
            }

            this.parsedMotif.add(new Pair<>(pitch,length));
        }
    }


    //sequence = changing pitches in melody, for example for using same motif in different chord
    // how do i encode shit in sequence, relative distance is the key-.

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
                    int choice = getChoice(1);
                    if(choice==0){
                        this.patternString += p.getValue0() + "t. ";
                    }else {
                        this.patternString += p.getValue0() + "t ";
                    }
                    break;
                case "s.":
                    choice = getChoice(1);
                    if(choice==0){
                        this.patternString += p.getValue0() + "s ";
                    }else {
                        this.patternString += p.getValue0() + "t. ";
                    }
                    break;
                case "i":
                    choice = getChoice(2);
                    switch (choice){
                        case 0:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 1:
                            this.patternString += p.getValue0() + "s ";
                            break;
                        case 2:
                            this.patternString += p.getValue0() + "t. ";
                            break;
                        case 3:
                            this.patternString += p.getValue0() + "t ";
                            break;
                    }
                    break;
                case "i.":
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
                case "q":
                    choice = getChoice(2);
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
                        case 4:
                            this.patternString += p.getValue0() + "s. ";
                            break;
                        case 5:
                            this.patternString += p.getValue0() + "s ";
                            break;
                        case 6:
                            this.patternString += p.getValue0() + "t. ";
                            break;
                        case 7:
                            this.patternString += p.getValue0() + "t ";
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
            }else {
                //here i should add something that sounds good, so what about chord note?
                choice=getChoice(3);
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
    public String createDivide(){
        return null;
    }

    public String decodeDivide(){
        return null;
    }

    public void developPattern(){

//        String motifToAdd = this.createSequence(3);
//        this.patternString += motifToAdd;
//
//         this.createAugment();
//        this.createDimunation();
//        Integer chord[] = new Integer[]{60, 64, 67, 69, 72, 74, 76, 69};
//        this.createExpand(chord);
          this.createContract();
    }

    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }

}

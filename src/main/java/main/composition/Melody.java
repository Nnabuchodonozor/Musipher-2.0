package main.composition;


public class Melody {
    Integer[] key;
    Integer lastNote = 60;

    String patternString;
    String strInput;
    public Melody(Integer[] key) {
    this.key = key;

    }

    public void addRandomMelody(String pattern, Instrument instrument, String input) {
        //TODO changeable constants f.e. different instrument, different range

        this.patternString = pattern;
        this.strInput = input;
        if(lastNote > 70){
            patternString += key[this.returnIndex(lastNote)-1] + "i ";
            lastNote = key[returnIndex(lastNote)-1];
        }
         else if(lastNote < 40){
            patternString += key[this.returnIndex(lastNote)+1] + "i ";
            lastNote = key[returnIndex(lastNote)+1];
        }else { // melody is in listenable interval
            boolean ascending = (this.getChoice(1) == 1 );
            boolean step = (this.getChoice(1) == 0 );
            if(step){
                this.addStep(ascending);
            }else {
                this.addLeap(ascending);
            }
        }
    }

    private void addStep( boolean ascending){
        int indexOfLastNote = this.returnIndex(lastNote);
        if(ascending){
            patternString += key[indexOfLastNote+1] + "i ";
            lastNote = key[indexOfLastNote+1];
        }else {
            patternString += key[indexOfLastNote-1] + "i ";
            lastNote = key[indexOfLastNote-1];
        }
    }

    private void addLeap( boolean ascending){
        int indexOfLastNote = this.returnIndex(lastNote);
        int choice = this.getChoice(2);
        if(ascending){
            patternString += key[indexOfLastNote+1+choice] + "i ";
            lastNote = key[indexOfLastNote+1+choice];
        }else {
            patternString += key[indexOfLastNote-1-choice] + "i ";
            lastNote = key[indexOfLastNote-1-choice];
        }
    }


    public String getPatternString() {
        return patternString;
    }

    public void setPatternString(String patternString) {
        this.patternString = patternString;
    }

    public String getStrInput() {
        return strInput;
    }

    public void setStrInput(String strInput) {
        this.strInput = strInput;
    }

    private int returnIndex(Integer note){
        for(int i = 0; i < key.length; i++ ){
            if(key[i].equals(note)){
                return i;
            }
        }
        return -1; //note not found, return negtive index
    }

    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }

}

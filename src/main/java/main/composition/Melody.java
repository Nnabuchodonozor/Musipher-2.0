package main.composition;


public class Melody {
    Integer[] key;
    Integer lastNote;

    String patternString;
    String strInput;
    public Melody(Integer[] key) {
    this.key = key;
    this.lastNote= key[35];
    }
    //random melody meaning generated based on some random input
    public String addRandomMelody(String pattern, Instrument instrument, String input) {
        this.patternString = pattern;
        this.strInput = input;
        if(lastNote > instrument.getUpperRange()){
//            patternString += key[this.returnIndex(lastNote)-1] + " ";
            patternString += key[this.returnIndex(lastNote)-1];
            lastNote = key[returnIndex(lastNote)-1];
        }
         else if(lastNote < instrument.getLowerRange()){
            patternString += key[this.returnIndex(lastNote)+1];
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
         return patternString;
    }

    public String decodeRandomMelody(Integer note){
        String result = "";
        if(note > lastNote){
            result+= "1";
            int b = this.returnIndex(lastNote);
            int a = this.returnIndex(note);
            if(a - b < 2){
                result +="0";
                lastNote = note;
            }else {
                result+= "1";
                result+= getChoiceString( (a - b - 1)   ,2);
                lastNote = note;
            }


        }else {
            result+= "0";
            int b = this.returnIndex(lastNote);
            int a = this.returnIndex(note);
            if(b - a < 2){
                result +="0";
                lastNote = note;
            }else {
                result+= "1";
                result+= getChoiceString( (b - a - 1)   ,2);
                lastNote = note;
            }
        }


        return result;
    }



    private void addStep( boolean ascending){
        int indexOfLastNote = this.returnIndex(lastNote);
        if(ascending){
            patternString += key[indexOfLastNote+1];
            lastNote = key[indexOfLastNote+1];
        }else {
            patternString += key[indexOfLastNote-1];
            lastNote = key[indexOfLastNote-1];
        }
    }

    private void addLeap( boolean ascending){
        int indexOfLastNote = this.returnIndex(lastNote);
        int choice = this.getChoice(2);
        if(ascending){
            patternString += key[indexOfLastNote+1+choice];
            lastNote = key[indexOfLastNote+1+choice];
        }else {
            patternString += key[indexOfLastNote-1-choice];
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
//        System.out.println(key.length);
        for(int i = 0; i < key.length; i++ ){
//            System.out.println(key[i]);
            if(key[i].equals(note)){
                return i;
            }
        }
        return -1; //note not found, return negtive index
    }

    public Integer[] parseMelody(){
        String[] strMelody = patternString.split(" ");
        Integer[] intMelody = new Integer[strMelody.length];

        for (int i = 0; i < strMelody.length; i++){
            intMelody[i] = Integer.parseInt(strMelody[i]);
        }
        return intMelody;
    }

    public Integer getLastNote() {
        return lastNote;
    }

    public void setLastNote(Integer lastNote) {
        this.lastNote = lastNote;
    }

    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }

    private String getChoiceString(int choice, int length){
        String s= Integer.toBinaryString(choice);
        while (s.length()<length){
            s = "0"+s;
        }
        return s;
    }

}

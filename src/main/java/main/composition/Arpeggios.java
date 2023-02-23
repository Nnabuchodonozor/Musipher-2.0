package main.composition;

public class Arpeggios {
    String patternString = "";

    public Arpeggios(String patternString) {

        this.patternString = patternString;
    }

    public String getPatternString() {
        return patternString;
    }

    public void setPatternString(String patternString) {
        this.patternString = patternString;
    }

    public void arpegiate(String[] notes, int length){
        int j = 0;
        int div = notes.length/2;
        for(int i = 0; i < length; i++){
            j=0;
            if(notes.length % 2 == 0){
                while (j <= div){
                    patternString+= notes[j++] +" ";
                }
                while (j < notes.length){
                    patternString+= notes[j++]+" ";
                }
            }else {
                while (j <= div){
                    patternString+= notes[j++]+" ";
                }
                patternString+= notes[j++]+" ";
                while (j < notes.length){
                    patternString+= notes[j++]+" ";
                }
            }
        }
    }


    //todo diferent types of arpegios that will be created

    // four note octave c - e - g - c - g - e - c
    // three clasic ceg ceg .. cea cea ... cfa cfa
    //

}

package main.composition;

import java.util.Arrays;
import java.util.List;

public class Key {

    String strInput;
    public Key(String strInput) {
        this.strInput = strInput;
    }

    //possible Integer
    //                 0 1  2 3  4 5 6  7  8 9 10 11 | 12 13  14 15  16 17 18  19 20  21 22  23 24 25 26  27  28 29 30  31 |
    // maj min dor    C C# D Eb E F F# G G# A Bb B  | C  C#  D  Eb  E  F  F#  G  G#  A  Bb  B  C  C#  D  Eb  E  F  F#  G  |G#  A  Bb  B
    public Integer[] generateKey(){
        int choice = getChoice(5);
        if(choice < 12){ // generate major
            return generateMajor(choice);
        }else if(choice < 24){ // generate minor
            return generateMinor(choice-12);
        }else if(choice < 29) { // generate dorian
            return generateDorian(choice-24+1); //24,25,26,27,28,29
        }else { //30,31
            return generateHarmonic(choice-29);
        }
    }

    private Integer[] generateHarmonic(int choice){
        int keyCounter = 0;
        Integer[] a = new Integer[128];
        for(int i = 0; i < 128; i++){

            if( (i % 12 == 1)||(i % 12 == 4)||(i % 12 == 6)||(i % 12 == 9)||(i % 12 == 10)  )
                continue;
            else
                a[keyCounter++]=i + choice;
        }
        a[keyCounter++]=-1;
        return Arrays.copyOfRange(a, 0, keyCounter);
    }

    public Integer[] generateKeyTest(int choice){
        if(choice < 12){ // generate major
            return generateMajor(choice);
        }else if(choice < 24){ // generate minor
            return generateMinor(choice-12);
        }else if(choice < 29) { // generate dorian
            return generateDorian(choice-24+1); //24,25,26,27,28,29
        }else { //30,31
            return generateHarmonic(choice-29);
        }
    }

    private Integer[] generateMajor(int choice){
        int keyCounter = 0;
        Integer[] a = new Integer[128];
            for(int i = 0; i < 128; i++){

               if( (i % 12 == 1)||(i % 12 == 3)||(i % 12 == 6)||(i % 12 == 8)||(i % 12 == 10)  )
                   continue;
               else
                   a[keyCounter++]=i + choice;
            }
        return a;
    }

    private Integer[] generateMinor(int choice){
        int keyCounter = 0;
        Integer[] a = new Integer[128];
        for(int i = 0; i < 128; i++){

            if( (i % 12 == 1)||(i % 12 == 4)||(i % 12 == 6)||(i % 12 == 9)||(i % 12 == 11)  )
                continue;
            else
                a[keyCounter++]=i + choice;
        }
        return a;
    }

    private Integer[] generateDorian(int choice){
        //create first C ionian which is C major
        int keyCounter = 0;
        Integer[] a = new Integer[128];
        for(int i = 0; i < 128; i++){

            if( (i % 12 == 1)||(i % 12 == 3)||(i % 12 == 6)||(i % 12 == 8)||(i % 12 == 10)  )
                continue;
            else
                a[keyCounter++]=i;
        }
        //then move it with a offset

        return Arrays.copyOfRange(a, choice, keyCounter);
    }

    public int getChoice(int length){
        String a = strInput.substring(0,length);
        strInput = strInput.substring(length);
        return Integer.parseInt(a, 2);
    }

    public String getStrInput() {
        return strInput;
    }

    public void setStrInput(String strInput) {
        this.strInput = strInput;
    }
}

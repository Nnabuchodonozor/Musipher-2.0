package main.composition;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

public class Harmony {
    List<Integer[]> chords = new ArrayList<>();
    Integer[] key;
    public Harmony(Integer [] key) {
        this.key=key;
        this.generateChords();
    }

    //major and minor and chr scales are using the same intervals
    // all chords have the same intervals
    private void generateChords(){
        //todo give me 8 chord notes pls
        int offset = 0;
        if(key[0]>5){
            offset = 7;
        }
        for(int i = 35; i < 42; i++){
            Integer [] chord = new Integer[8];
            if(i < 38){
                chord[0] = key[i-offset];
                chord[1] = key[i + 2-offset];
                chord[2] = key[i + 4-offset];
                chord[3] = key[i - 3-offset];
                chord[4] = key[i - 5-offset];
                chord[5] = key[i + 7-offset];
                chord[6] = key[i + 9-offset];
                chord[7] = key[i + 11-offset];
            }else {
                int a = 7;
                chord[0] = key[i-a];
                chord[1] = key[i + 2-a];
                chord[2] = key[i + 4-a];
                chord[3] = key[i - 3-a];
                chord[4] = key[i - 5-a];
                chord[5] = key[i + 7-a];
                chord[6] = key[i + 9-a];
                chord[7] = key[i + 11-a];
            }
            this.chords.add(chord);
        }
        System.out.println();
    }



    public void printChords(){
        int counter = 0;
        for (Integer[] chord : this.chords){
            System.out.println("chrod"+counter++);
            for (Integer i : chord){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

package main.composition;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

public class Harmony {
    List<Integer[]> chords = new ArrayList<>();
    Integer[] key;
    public Harmony(Integer [] key) {
        this.key=key;
        if(key[2]-key[1]==2){
            this.generateMajChords();
        }else if(key[5]-key[4]==1){
            this.generateMinChords();
        }else {
            this.generateDorChords();
        }
    }

    private void generateMajChords(){
        //todo give me 8 chord notes pls
        int offset = 0;
        if(key[0]>5){
            offset = 12;
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
                int a = 12;
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
    private void generateMinChords(){
        int offset = 0;
        if(key[0]>5){
            offset = 12;
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
                int a = 12;
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
    private void generateDorChords(){
        int offset = 0;
        if(key[0]>5){
            offset = 12;
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
                int a = 12;
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

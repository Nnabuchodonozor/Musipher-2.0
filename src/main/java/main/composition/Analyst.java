package main.composition;

import java.util.ArrayList;
import java.util.List;

public class Analyst {

    List<ArrayList<String>> parsedSong;
    String strOutput = "";

    public Analyst(List<ArrayList<String>> parsedSong) {
        this.parsedSong = parsedSong;
    }

    public String analyzeSong(){
        decomposeKey();


        return strOutput;
    }

    public void decomposeKey(){
  ArrayList<String> firstMelody = parsedSong.get(0); //todo change to 1
        if(firstMelody.get(1).startsWith("R")){
            Key key = new Key("");
            key.generateKeyTest( Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60);
        }else {
            int a = Integer.parseInt(firstMelody.get(0),0,2,10);
            int b = Integer.parseInt(firstMelody.get(1),0,2,10);
            if(b > a){

            }else if(a > b){

            }else {

            }
        }
    }
}

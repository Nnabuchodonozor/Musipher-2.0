package main.composition;

import java.util.ArrayList;
import java.util.List;

public class Analyst {

    List<ArrayList<String>> parsedSong;
    String strOutput = "";
    Key key;
    public Analyst(List<ArrayList<String>> parsedSong) {
        this.parsedSong = parsedSong;
    }

    public String analyzeSong(){
        decomposeKey();


        return strOutput;
    }

    public void decomposeKey(){
  ArrayList<String> firstMelody = parsedSong.get(0); //todo change to 1
        key = new Key("");
        if(firstMelody.get(1).startsWith("R")){
            int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
            key.generateKeyTest( choice );
            strOutput+=getChoiceString(choice,5);
            System.out.println(strOutput);
        }else {
            int a = Integer.parseInt(firstMelody.get(0),0,2,10);
            int b = Integer.parseInt(firstMelody.get(1),0,2,10);
            if(b > a){
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                key.generateKeyTest( choice + 12 );
                strOutput+=getChoiceString(choice+ 12,5);
                System.out.println(strOutput);
            }else if(a > b){
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                key.generateKeyTest( choice +24-1 );
                strOutput+=getChoiceString(choice+24-1,5);
                System.out.println(strOutput);
            }else {
                int choice = Integer.parseInt(firstMelody.get(0) ,0,2,10)- 60;
                key.generateKeyTest( choice + 29 );
                strOutput+=getChoiceString(choice+ 29,5);
                System.out.println(strOutput);
            }
        }
    }

    private String getChoiceString(int choice, int length){
        String s= Integer.toBinaryString(choice);
        while (s.length()<length){
            s = "0"+s;
        }
        return s;
    }

}

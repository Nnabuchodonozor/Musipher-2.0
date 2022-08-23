import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticUtils {
    Map<String, String> base32Map = new HashMap<String, String>();

    public SemanticUtils() {
        base32Map.put("A","0000");
        base32Map.put("B","0000");
        base32Map.put("C","0000");
        base32Map.put("D","0000");
        base32Map.put("E","0000");
        base32Map.put("F","0000");
        base32Map.put("G","0000");
        base32Map.put("H","0000");
        base32Map.put("I","0000");
        base32Map.put("J","0000");
        base32Map.put("K","0000");
        base32Map.put("L","0000");
        base32Map.put("M","0000");
        base32Map.put("N","0000");
        base32Map.put("O","0000");
        base32Map.put("P","0000");
        base32Map.put("Q","0000");
        base32Map.put("R","0000");
        base32Map.put("S","0000");
        base32Map.put("T","0000");
        base32Map.put("U","0000");
        base32Map.put("V","0000");
        base32Map.put("W","0000");
        base32Map.put("X","0000");
        base32Map.put("Y","0000");
        base32Map.put("Z","0000");
        base32Map.put("2","0000");
        base32Map.put("3","0000");
        base32Map.put("4","0000");
        base32Map.put("5","0000");
        base32Map.put("6","0000");
        base32Map.put("7","0000");
    }

    public void encryptMIDIFromText(String text){
        //ascii text => base 32 alphabet string => byte array => encrypted byte array => MIDI
        String base32String = turnAsciiToBase32(text);


    }




    public void decryptMIDIToText(String fileName, String cipherKey){}
    public void encryptMIDIFromFile(){}
    public void decryptMIDIToFile(){}

    private String turnAsciiToBase32(String input){


        return "";
    }
}

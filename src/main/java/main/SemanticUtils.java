package main;
import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class SemanticUtils {
    Map<String, String> base32Map = new HashMap<String, String>();
    StreamCipher streamCipher = new StreamCipher();
    byte[] iVString=null;
    String seed="";
    public SemanticUtils() {
        base32Map.put("a","00000");
        base32Map.put("b","00001");
        base32Map.put("c","00010");
        base32Map.put("d","00011");
        base32Map.put("e","00100");
        base32Map.put("f","00101");
        base32Map.put("g","00110");
        base32Map.put("h","00111");
        base32Map.put("i","01000");
        base32Map.put("j","01001");
        base32Map.put("k","01010");
        base32Map.put("l","01011");
        base32Map.put("m","01100");
        base32Map.put("n","01101");
        base32Map.put("o","01110");
        base32Map.put("p","01111");
        base32Map.put("q","10000");
        base32Map.put("r","10001");
        base32Map.put("s","10010");
        base32Map.put("t","10011");
        base32Map.put("u","10100");
        base32Map.put("v","10101");
        base32Map.put("w","10110");
        base32Map.put("x","10111");
        base32Map.put("y","11000");
        base32Map.put("z","11001");
        base32Map.put("2","11010");
        base32Map.put("3","11011");
        base32Map.put("4","11100");
        base32Map.put("5","11101");
        base32Map.put("6","11110");
        base32Map.put("7","11111");

    }

//ascii => base32 => byte array [open] => byte array[encrypted] => strinput => MIDI => strinput =>
    public void encryptToMIDIFromText(String text) throws Exception{
        iVString = "fkdgflfdsfvdeirt".getBytes();
        seed = "some seed";

        //ascii text => base 32 alphabet string => byte array => encrypted byte array => MIDI
        String base32String = turnAsciiToBase32(text.toLowerCase(Locale.ROOT));
        byte[] openText = turnBase32ToByte(base32String);

            byte[] enc = streamCipher.encrypt(iVString,seed,openText);
            FileOutputStream fos = new FileOutputStream(new File("encryptedBytes"));
            fos.write(iVString);
            fos.write(enc);
            fos.close();
            }

    public void decryptFromMIDIToText(String encryptedPath, String keyPath){
        File f1 = new File(encryptedPath);
        File f2 = new File(keyPath);
        try {
            byte[] cypheredBytesWithIV = Files.readAllBytes(f1.toPath());
            byte[] iVBytes = Arrays.copyOfRange(cypheredBytesWithIV,0,16);
            byte[] cypheredBytes = Arrays.copyOfRange(cypheredBytesWithIV,16,cypheredBytesWithIV.length);
            byte[] keyBytes = Files.readAllBytes(f2.toPath());
            byte[] decryptedBytes = streamCipher.decrypt(iVBytes, keyBytes,cypheredBytes);
            String base32String = this.turnByteToBase32(decryptedBytes);
            System.out.println("decyphered " + turnBase32ToAscii(base32String));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void encryptToMIDIFromFile(String fileName) throws Exception {
        iVString = "fkdgflfdsfvdeirt".getBytes();
        seed = "some seed";
        String[] strings = fileName.split("\\.");
        byte[] fExtension = strings[1].getBytes();
        Integer i = (strings[1].length());
        byte fExtensionLength = i.byteValue();
        File openDataFile = new File(fileName);
        byte[] openBytes = Files.readAllBytes(openDataFile.toPath());
        byte[] enc = streamCipher.encrypt(iVString,seed,openBytes);
        FileOutputStream fos = new FileOutputStream(new File("encryptedBytes"));
        fos.write(fExtensionLength);
        fos.write(fExtension);
        fos.write(iVString);
        fos.write(enc);
        fos.close();
    }

    public void decryptFromMIDIToFile(String encryptedPath, String keyPath) throws Exception{
        File f1 = new File(encryptedPath);
        File f2 = new File(keyPath);
        byte[] cipheredBytesWithIV = Files.readAllBytes(f1.toPath());
        Byte b = cipheredBytesWithIV[0];
        int i = b.intValue();
        byte[] fExtension = Arrays.copyOfRange(cipheredBytesWithIV,1,1+i);
        String stringFileExtension = new String(fExtension, StandardCharsets.UTF_8);
        byte[] iVBytes = Arrays.copyOfRange(cipheredBytesWithIV,1+i,1+i+16);
        byte[] cypheredBytes = Arrays.copyOfRange(cipheredBytesWithIV,1+i+16,cipheredBytesWithIV.length);
        byte[] keyBytes = Files.readAllBytes(f2.toPath());
        byte[] decryptedBytes = streamCipher.decrypt(iVBytes, keyBytes,cypheredBytes);
        FileOutputStream fos = new FileOutputStream(new File("decryptedFile."+stringFileExtension));
        fos.write(decryptedBytes);
        fos.close();
    }

    public String generateStreamCipherKeyAndIV(){
        seed="";

        int lengthOfSeed = (int) ((Math.random() * (20 - 10)) + 10);

        for(int i = 0; i < lengthOfSeed; i++) {
            int seedLetter = (int) ((Math.random() * (25 - 0)) + 0);
            int lowerCaseRandom = (int) ((Math.random() * (1 - 0)) + 0);
            if(lowerCaseRandom==0){
                seed = seed + (char) (seedLetter+65);
            }else {
                seed = seed + (char) (seedLetter+97);
            }
        }
        byte[] b = new byte[16];
        new Random().nextBytes(b);
        iVString = b;
        return "You have generated seed and IV for key generation, key will be stored in root folder \n" + seed + " " + iVString;
    }


    private String turnAsciiToBase32(String input){
        input = input.replaceAll(" ","");
        for (Map.Entry<String, String> entry : base32Map.entrySet()) {
            input = input.replaceAll(entry.getKey(),entry.getValue());
        }
        return input;
    }

    private byte[] turnBase32ToByte(String base32String){
        byte[] bts = new byte[(base32String.length() / 8)+1];
        int openBytesCounter = 0;
        while (base32String.length()%8!=0){
            base32String+="0";
        }

        while(base32String.length()!=0){
            Integer tempinteger = Integer.parseInt(base32String.substring(0,8), 2);
            byte b = tempinteger.byteValue();
            base32String=base32String.substring(8);
            bts[openBytesCounter]=b;
            openBytesCounter++;
        }
        return bts;
    }

    private String turnByteToBase32(byte[] bytes){
        String base32String ="";
        for(int i = 0; i < bytes.length; i++) {
           base32String += Integer.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1);
        }

    return base32String;
    }

    private String turnBase32ToAscii(String base32String){
        String output = "";
        for(int i = 0; i < (base32String.length() / 5); i++){
            for(Map.Entry<String,String> entry : base32Map.entrySet()){
                if(entry.getValue().equals(base32String.substring(0+(5*i),5+(5*i)))){
                    output+= entry.getKey();
                    break;
                }
            }
        }

        return output;
    }

}
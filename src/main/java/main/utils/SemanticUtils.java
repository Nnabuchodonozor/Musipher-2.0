package main.utils;
import main.security.StreamCipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class SemanticUtils {
    Map<String, String> base32Map = new HashMap<String, String>();
    StreamCipher streamCipher = new StreamCipher();
    MidiUtils midiUtils = new MidiUtils();
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
    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   text    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public void encryptToMIDIFromText(String text, String password) throws Exception{
        generateTrueRandomIV();

        //ascii text => base 32 alphabet string => byte array => encrypted byte array => MIDI
        String base32String = turnAsciiToBase32(text.toLowerCase(Locale.ROOT));
        byte[] openText = turnBase32ToByte(base32String);

            byte[] enc = streamCipher.encrypt(iVString,password,openText);
//            midiUtils.composeMIDI(enc);

            FileOutputStream fos = new FileOutputStream(new File("encryptedBytes"));
            fos.write(iVString);
            fos.write(enc);
            fos.close();

    }

    public void decryptFromMIDIToText(String encryptedPath, String password) throws FileNotFoundException {
        File f1 = new File(encryptedPath);
        FileInputStream fis = new FileInputStream(f1);
//        File f2 = new File(keyPath);
        try {
//            byte[] cypheredBytesWithIV = midiUtils.decomposeMIDI(encryptedPath);
            byte[] cypheredBytesWithIV = fis.readAllBytes();
            byte[] iVBytes = Arrays.copyOfRange(cypheredBytesWithIV,0,16);
            byte[] saltBytes = Arrays.copyOfRange(cypheredBytesWithIV, 16, 32);
            byte[] cypheredBytes = Arrays.copyOfRange(cypheredBytesWithIV,32,cypheredBytesWithIV.length);
            streamCipher.setSalt(saltBytes);
            byte[] decryptedBytes = streamCipher.decrypt(iVBytes, password,cypheredBytes);
            String base32String = this.turnByteToBase32(decryptedBytes);
            System.out.println("deciphered " + turnBase32ToAscii(base32String));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void encryptToMIDIFromFile(String fileName, String password) throws Exception {
        generateTrueRandomIV();

        String[] strings = fileName.split("\\.");
        byte[] fExtension = strings[1].getBytes();
        Integer i = (strings[1].length());
        byte fExtensionLength = i.byteValue();
        File openDataFile = new File(fileName);
        byte[] openBytes = Files.readAllBytes(openDataFile.toPath());
        byte[] enc = streamCipher.encrypt(iVString,password,openBytes);
        midiUtils.composeMIDI(enc);

        //        FileOutputStream fos = new FileOutputStream(new File("encryptedBytes"));
//        fos.write(fExtensionLength);
//        fos.write(fExtension);
//        fos.write(iVString);
//        fos.write(enc);
//        fos.close();
    }

    public void decryptFromMIDIToFile(String encryptedPath, String password) throws Exception{
//        File f1 = new File(encryptedPath);
//        File f2 = new File(keyPath);
        byte[] cipheredBytesWithIV = midiUtils.decomposeMIDI(encryptedPath);
        Byte b = cipheredBytesWithIV[0];
        int i = b.intValue();
        byte[] fExtension = Arrays.copyOfRange(cipheredBytesWithIV,1,1+i);
        String stringFileExtension = new String(fExtension, StandardCharsets.UTF_8);
        byte[] iVBytes = Arrays.copyOfRange(cipheredBytesWithIV,1+i,1+i+16);
        byte[] cypheredBytes = Arrays.copyOfRange(cipheredBytesWithIV,1+i+16,cipheredBytesWithIV.length);

        byte[] decryptedBytes = streamCipher.decrypt(iVBytes, password,cypheredBytes);
        FileOutputStream fos = new FileOutputStream(new File("decryptedFile."+stringFileExtension));
        fos.write(decryptedBytes);
        fos.close();
    }

    public String generateTrueRandomIV()throws NoSuchAlgorithmException {
        byte[] b = new byte[16];
        SecureRandom random = SecureRandom.getInstance( "SHA1PRNG" );
        random.nextBytes( b );
        iVString = b;
        return "";
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

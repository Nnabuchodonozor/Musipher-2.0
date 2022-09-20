package main;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class test {
    public static void main(String[] args) {
        try {
            SemanticUtils semanticUtils = new SemanticUtils();
//            semanticUtils.encryptToMIDIFromFile("image.jpg");
//            semanticUtils.decryptFromMIDIToFile("encryptedBytes","encryptionKey");

//            File file = new File("song1.mp3"); // Sorrow.mp3 is the local mp3 music needs to be sent
//            FileInputStream loc = new FileInputStream(file);
//            byte[] rawMp3 = new byte[(int)file.length()];
//            loc.read(rawMp3);
//            System.out.println(rawMp3);
//            File file2 = new File("song2.mp3");
//            FileOutputStream fos = new FileOutputStream(file2);
//            fos.write(rawMp3);
//            fos.flush();
//            fos.close();

      String openText = "this is sacred message";
      //I need to provide a seed
            String seed = "seed";
            //iv should generate by itself
            semanticUtils.encryptToMIDIFromText(openText, seed);



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

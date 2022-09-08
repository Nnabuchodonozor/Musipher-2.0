package main;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class test {
    public static void main(String[] args) {
        try {
            SemanticUtils semanticUtils = new SemanticUtils();
            semanticUtils.encryptToMIDIFromFile("image.jpg");
            semanticUtils.decryptFromMIDIToFile("encryptedBytes","encryptionKey");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

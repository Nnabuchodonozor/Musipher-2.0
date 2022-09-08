package main;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class test {
    public static void main(String[] args) {
        
        SemanticUtils semanticUtils = new SemanticUtils();
        semanticUtils.encryptToMIDIFromText("A");
        semanticUtils.decryptFromMIDIToText("encryptedBytes","encryptionKey");


    }
}

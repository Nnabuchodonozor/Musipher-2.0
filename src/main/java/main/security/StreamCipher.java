package main.security;

import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.print.DocFlavor;

public class StreamCipher {

    private byte[] salt = null;

    public StreamCipher() {
    }


    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] encrypt(byte[] iv, String password, byte[] cleartext) throws Exception {

        byte[] rawKey = getRawKey(password,null);
        return  encrypt(iv,rawKey, cleartext);
    }


    private byte[] getRawKey(String password, byte[] previousSalt) throws Exception {
//      previous implementation incuded generating password from seed, here password will be used to create hash

        //KeyGenerator kgen = KeyGenerator.getInstance("AES");
        PasswordHash pHash = new PasswordHash();



        byte[] hash=  pHash.createHash(password, previousSalt);
        salt = pHash.getSalt();
        return hash;
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        sr.setSeed(hashedSeed);
//        kgen.init(128, sr); // 192 and 256 bits may not be available
//        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        FileOutputStream fos = new FileOutputStream(new File("encryptionKey"));
//        fos.write(raw);


    }

//    byte[] iv = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };

    private byte[] encrypt(byte[] iv ,byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(clear);
        byte [] connectedEncrypted = new byte[encrypted.length + salt.length];
        int i = 0;
        for(; i < salt.length; i++){
            connectedEncrypted[i] = salt[i];
        }
        for(int j = 0; j < encrypted.length; j++) {
            connectedEncrypted[i+j] = encrypted[j];
        }
            return connectedEncrypted;
    }

    public byte[] decrypt(byte[] iv ,String password, byte[] encrypted) throws Exception {
        byte[] raw = getRawKey(password,salt);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public String toHex(String txt) {
        return toHex(txt.getBytes());
    }
    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        return result;
    }

    public String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2*buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }
    private final static String HEX = "0123456789ABCDEF";
    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
    }
}

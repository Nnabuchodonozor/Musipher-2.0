import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class test {
    public static void main(String[] args) {
        // Input byte array.
        try {
            // keygen

            byte[] seed = "some seed".getBytes();
            byte[] clear = "some text".getBytes();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);
            kgen.init(128, sr); // 192 and 256 bits may not be available
            SecretKey skey = kgen.generateKey();
            byte[] raw = skey.getEncoded();


            // ivgen keygen
//            byte[] iv = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };
            byte[] iv = "fkdgflfdsfvdeirt".getBytes();
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(clear);
            String encString = new String(encrypted);
            System.out.println(encString +"   " + encrypted.length);
            encrypted[encrypted.length-1]=0;


            //xxxxxxxxxx

            IvParameterSpec ivSpec2 = new IvParameterSpec(iv);

            SecretKeySpec skeySpec2 = new SecretKeySpec(raw, "AES");
            Cipher cipher2 = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec2, ivSpec);


            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println(new String(decrypted));
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }

    }
}

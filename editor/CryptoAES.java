package editor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

public class CryptoAES implements Crypto {
    private SecretKey secretKey;


    public CryptoAES() {
    }

    @Override
    public String Encrypt(String Str, String Key ){
        return Crypt(Str, Key, Cipher.ENCRYPT_MODE);

    }

    @Override
    public String Decrypt(String Str, String Key ){
        return Crypt(Str, Key, Cipher.DECRYPT_MODE);
    }


    public String Crypt(String Str, String Key, int CliperMode ){

        try {


            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(Key.toCharArray(), " ".getBytes(), 65536, 256);
            SecretKey tmp = keyfactory.generateSecret(spec);
            secretKey = new SecretKeySpec(tmp.getEncoded(),"AES");



            byte[] bytes = Str.getBytes("windows-1252");




            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(CliperMode, this.secretKey);
            byte [] output = cipher.doFinal(bytes);


            String s = new String(output,"windows-1252");


            return s;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


}

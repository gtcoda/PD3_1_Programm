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
    public String CipherMethod(){
        return "AES";
    }

    @Override
    public void SetKey(String Key){
        try {
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(Key.toCharArray(), " ".getBytes(), 65536, 256);
        SecretKey tmp = keyfactory.generateSecret(spec);
        secretKey = new SecretKeySpec(tmp.getEncoded(),"AES");
        } catch (Exception e){
            e.printStackTrace();
            secretKey = null;
        }
    }

    @Override
    public byte[] Encrypt(String Str){
        try {

            byte[] bytes = Str.getBytes();

            byte[] out = Crypt(bytes, Cipher.ENCRYPT_MODE);
            return out;

        } catch (Exception e){
            return null;
        }

    }

    @Override
    public String Decrypt(byte[] blob){
        try{

            byte[] text = Crypt( blob, Cipher.DECRYPT_MODE);

            return new String(text);

        } catch (Exception e){
            return null;
        }
    }


    public byte[] Crypt(byte[] input, int CliperMode ) throws Exception {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(CliperMode, this.secretKey);
            byte [] output = cipher.doFinal(input);

            return output;
    }


}

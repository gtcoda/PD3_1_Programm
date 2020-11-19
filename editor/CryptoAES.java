package editor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class CryptoAES implements Crypto {
    private SecretKey secretKey;


    public CryptoAES() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            this.secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] makeAes(byte[] rawMessage, int cipherMode){
        try {

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, this.secretKey);
            byte [] output = cipher.doFinal(rawMessage);
            return output;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String Encrypt(String Str, String Key ){

        return "Зашифровано AES";
    }

    @Override
    public String Decrypt(String Str, String Key ){
        return "Расшифровано AES";
    }


}

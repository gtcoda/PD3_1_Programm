package editor;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.lang.reflect.*;

public class CryptoFactory {

    public static Crypto instance;

    static Crypto C_AES = null;
    static Crypto C_SKP = null;

    public enum CryptoTypes {
        AES, // AES
        SKP, // Single key permutation
        ALMOND
    }

    public Crypto Factory(CryptoTypes type){
        Crypto toReturn = null;
        switch (type) {
            case AES:
                if(C_AES != null){
                    toReturn = C_AES;
                }
                else {
                    toReturn = new CryptoAES();
                }
                break;
            case SKP:
                if(C_SKP != null){
                    toReturn = C_SKP;
                }
                else {
                    toReturn = new CryptoSKP();
                }
                break;
            case ALMOND:

                break;
            default:
                throw new IllegalArgumentException("Wrong doughnut type:" + type);
        }
        return toReturn;
    }


    public Crypto Activity(){
        if(instance != null){
           return instance;
        }
        return null;
    }

}

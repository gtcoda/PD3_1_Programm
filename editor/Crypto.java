package editor;



public interface Crypto {

    enum CipherMethod{
        AES,
        SKP
    }


    String CipherMethod();

    void SetKey(String Key);

    // Зашифровать строку
    byte[] Encrypt(String Str) throws Exception;

    // Расшифровать строку
    String Decrypt(byte[] blob);


}

package editor;



public interface Crypto {

    String CipherMethod();

    void SetKey(String Key);

    // Зашифровать строку
    byte[] Encrypt(String Str);

    // Расшифровать строку
    String Decrypt(byte[] blob);


}

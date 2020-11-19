package editor;



public interface Crypto {



    // Зашифровать строку
    String Encrypt(String Str, String Key );

    // Расшифровать строку
    String Decrypt(String Str, String Key );

}

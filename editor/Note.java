package editor;
// Запись
public class Note {
    public int note_id;
    public byte[] title_crypt;
    public String title_decrypt;

    public byte[] text_crypt;
    public String text_decrypt;

    public String Cipher;

    public void Set_decrypt(String title, String text, String Ciph){
        title_decrypt = title;
        text_decrypt = text;
        Cipher = Ciph;
        Crypt();
    }

    private void Crypt(){
        Crypto CC = (new CryptoFactory()).Activity();
        title_crypt = CC.Encrypt(title_decrypt);
        text_crypt = CC.Encrypt(text_decrypt);
    }

    public void Set_encrypt(int id, byte[] title, byte[] text, String Ciph){
        title_crypt = title;
        text_crypt = text;
        note_id = id;
        Cipher = Ciph;
        Decrypt();
    }

    private void Decrypt(){
        Crypto CC = (new CryptoFactory()).Activity();
        title_decrypt = CC.Decrypt(title_crypt);
        text_decrypt = CC.Decrypt(text_crypt);
    }

}

package editor;
// Запись
public class Note {
    public int note_id;
    public byte[] title_crypt;
    public String title_decrypt;

    public byte[] text_crypt;
    public String text_decrypt;

    public String Cipher;

    // Добавить заголовок записи
    public void setTitle(String title){
        title_decrypt = title;
        Crypt();
    }
    // Добавить текст записи
    public void setText(String text){
        text_decrypt = text;
        Crypt();
    }

    public void setDecrypt(String title, String text, String Ciph){
        title_decrypt = title;
        text_decrypt = text;
        Cipher = Ciph;
        Crypt();
    }

    public void setEncrypt(int id, byte[] title, byte[] text, String Ciph){
        title_crypt = title;
        text_crypt = text;
        note_id = id;
        Cipher = Ciph;
        Decrypt();
    }

    private void Crypt(){
        Crypto CC = (new CryptoFactory()).Activity();
        title_crypt = CC.Encrypt(title_decrypt);
        text_crypt = CC.Encrypt(text_decrypt);
    }

    private void Decrypt(){
        Crypto CC = (new CryptoFactory()).Activity();
        title_decrypt = CC.Decrypt(title_crypt);
        text_decrypt = CC.Decrypt(text_crypt);
    }

}

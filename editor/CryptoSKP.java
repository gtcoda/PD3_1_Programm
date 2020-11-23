package editor;

public class CryptoSKP implements Crypto {



   public CryptoSKP(){}

   @Override
   public String CipherMetod(){
       return "SKP";
   }

    @Override
    public void SetKey(String Key){

    }


    @Override
    public byte[] Encrypt(String Str){
       byte[] b = {0,1};

        return b;
    }

    @Override
    public String Decrypt(byte[] blob){
        return "";
    }

}

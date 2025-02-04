package editor;

import java.nio.charset.Charset;

public class CryptoSKP implements Crypto {

   public CryptoSKP(){}

   @Override
   public String CipherMethod(){
       return "SKP";
   }

    @Override
    public void SetKey(String Key){ }


    @Override
    public byte[] Encrypt(String Str){
       byte input[] = {};

       try{
           input = Str.getBytes();
           for(int i = 2; i<input.length; i = i+3){
               byte tmp;
               tmp = input[i];
               input[i] = input[i-2];
               input[i-2] = tmp;
           }

           return input;
       }
       catch(Exception ex){

       }
        return input;

    }

    @Override
    public String Decrypt(byte[] blob){
        byte input[] = blob;

        for(int i = input.length;  i>2; i = i-3){
            byte tmp;
            tmp = input[i - 1];
            input[i-1] = input[i-2 -1];
            input[i-2-1] = tmp;
        }

        return new String(input);

    }

}

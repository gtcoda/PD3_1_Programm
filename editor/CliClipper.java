package editor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class CliClipper {
    private SecretKey secretKey;


    public void EncryptFile(String inputfile, String outputfile, String Key) throws Exception  {
        SetKey(Key);

        FileInputStream fin = new FileInputStream(inputfile);
        System.out.printf("File size: %d bytes \n", fin.available());

        byte[] in = fin.readAllBytes();
        byte[] out = Encrypt(in);
        FileOutputStream fos=new FileOutputStream(outputfile);
        fos.write(out, 0, out.length);
    }


    public void DecryptFile(String inputfile, String outputfile, String Key) throws Exception  {
        SetKey(Key);

        FileInputStream fin = new FileInputStream(inputfile);
        System.out.printf("File size: %d bytes \n", fin.available());

        byte[] in = fin.readAllBytes();
        byte[] out = Decrypt(in);
        FileOutputStream fos=new FileOutputStream(outputfile);
        fos.write(out, 0, out.length);
    }




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

    public byte[] Encrypt(byte[] bytes){
        try {
            byte[] out = Crypt(bytes, Cipher.ENCRYPT_MODE);
            return out;

        } catch (Exception e){
            return null;
        }

    }


    public byte[] Decrypt(byte[] blob){
        try{

            byte[] text = Crypt( blob, Cipher.DECRYPT_MODE);

            return text;

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



    public byte[] CruptPR(byte[] input){
        byte output[] = {};
        byte res[][] = {};
        int colums = input.length/4;
        int x = 0;



        for(int i = 2; i<input.length; i = i+3){
            byte tmp;
            tmp = input[i];
            input[i] = input[i-2];
            input[i-2] = tmp;
        }


        for(int i = input.length;  i>2; i = i-3){
            byte tmp;
            tmp = input[i - 1];
            input[i-1] = input[i-2 -1];
            input[i-2-1] = tmp;
        }

        return output;
     }

}

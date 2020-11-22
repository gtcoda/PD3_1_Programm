package editor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.web.HTMLEditor;

import java.sql.SQLException;


import javax.crypto.Cipher;

public class Main extends Application{


    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        CryptoAES aes = new CryptoAES();

        String s = "Hello";

        String encrypt =  aes.Encrypt(s,"123");

        System.out.println("Encrypt: " + encrypt);

        String decrypt =  aes.Decrypt(encrypt,"123");

        System.out.println("Decrypt: " + decrypt);



        DataBase DB = DataBase.getInstance("1");
        Scene scene;

        SceneManager SM = SceneManager.getInstance();
        SM.setStage(stage);

        SM.setScene("KeyRequest.fxml");

    }






}
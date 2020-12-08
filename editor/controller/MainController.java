package editor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MainController  {

    public TextArea SText;
    public TextField Key;
    @FXML
    public TextArea CipherText;
    @FXML
    public  Button Encrypt;

    public Label lbl;


    @FXML
    private void click(ActionEvent event) {
        //Encrypt.setText("You've clicked!");

        String KeyStr = Key.getText();

       //lbl.setText("Текст: \n" + SText.getText() + "\n Ключ: " + KeyStr);

        CipherText.setText("Текст: \n" + SText.getText() + "\n Ключ: " + KeyStr);
    }



    @FXML
    private void Key(ActionEvent event) {
        String KeyStr = Key.getText();

        // lbl.setText("Текст: \n" + SText.getText() + "\n Ключ: " + KeyStr);

        // CipherText.setText("Текст: \n" + text + "\n Ключ: " + KeyStr);
    }


}
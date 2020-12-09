package editor.controller;

import editor.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.MultipleSelectionModel;

public class KeyRequestController extends Controller {

    @FXML
    ComboBox Source;
    @FXML
    ComboBox TypeEncrypt;
    @FXML
    TextField Password;

    ObservableList<String> langs = FXCollections.observableArrayList("AES", "SKP");
    //ObservableList<String> langsSource = FXCollections.observableArrayList("DB", "File");

    @Override
    public void initialize(URL location, ResourceBundle resources){

       TypeEncrypt.setItems(langs);

       TypeEncrypt.setValue("AES"); // устанавливаем выбранный элемент по умолчанию

       // Source.setItems(langsSource);
       // Source.setValue("DB");
    }

    @FXML
    public void CheckEncrypt() throws Exception{

        if (TypeEncrypt.getValue() == "AES"){
            Crypto CR = (new CryptoFactory()).Factory(CryptoFactory.CryptoTypes.AES);
            CR.SetKey(Password.getText());
        }
        else if (TypeEncrypt.getValue() == "SKP"){
            Crypto CR = (new CryptoFactory()).Factory(CryptoFactory.CryptoTypes.SKP);
            CR.SetKey(Password.getText());
        }

        Notes N = Notes.getInstance();
        N.getNoteDB();

        SceneManager SM = SceneManager.getInstance();

        SM.setScene(SceneManager.AvabilityScene.Display);

    }


}

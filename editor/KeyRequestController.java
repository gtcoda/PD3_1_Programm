package editor;

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
    ComboBox TypeEncrypt;
    @FXML
    TextField Password;

    ObservableList<String> langs = FXCollections.observableArrayList("AES", "SKP");

    @Override
    public void initialize(URL location, ResourceBundle resources){

       TypeEncrypt.setItems(langs);

        TypeEncrypt.setValue("AES"); // устанавливаем выбранный элемент по умолчанию
    }


    public void CheckEncrypt() throws Exception{

        if (TypeEncrypt.getValue() == "AES"){
            Crypto CR = (new CryptoFactory()).Factory(CryptoFactory.CryptoTypes.AES);
            CR.SetKey(Password.getText());
        }
        else if (TypeEncrypt.getValue() == "SKP"){
            Crypto CR = (new CryptoFactory()).Factory(CryptoFactory.CryptoTypes.SKP);
            CR.SetKey(Password.getText());
        }

        SceneManager SM = SceneManager.getInstance();

        SM.setScene("Display.fxml");

    }


}

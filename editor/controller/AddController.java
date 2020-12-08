package editor.controller;

import editor.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.web.*;
import java.util.*;

import  javafx.fxml.Initializable;
import java.net.URL;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.MultipleSelectionModel;


public class AddController extends Controller {
    @FXML
    public HTMLEditor HTMLText;
    @FXML
    TextField Title;

    ///////////
    DataBase DB = DataBase.getInstance();

    @FXML
    private void add(ActionEvent event) throws Exception{

        Crypto CR = (new CryptoFactory()).Activity();

        Map<String,byte[]> rs = new HashMap<String,byte[]>();

        rs.put("text",CR.Encrypt(HTMLText.getHtmlText()));
        rs.put("title",CR.Encrypt(Title.getText()));

        DB.insert(rs);

        // Закроем модальное окно
        SceneManager SM = SceneManager.getInstance();
        SM.hideModalWindows();
    }



}
package editor.controller;

import editor.Controller;
import editor.Crypto;
import editor.CryptoFactory;
import editor.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.web.*;
import java.util.*;

import java.net.URL;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.MultipleSelectionModel;


public class EditController extends Controller {
    @FXML
     public HTMLEditor HTMLText;
    @FXML
     ListView ListT;
    @FXML
     TextField Title;

    // текущая запись
     private int id;

    Map<byte[], Integer> listEncrypt = new HashMap<byte[], Integer>();
    Map<String, Integer> listDecrypt = new HashMap<String, Integer>();

    public ObservableList<String> langs = FXCollections.observableArrayList();

    ///////////
    DataBase DB = DataBase.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources){

        MultipleSelectionModel<String> langsSelectionModel = ListT.getSelectionModel();

        ListT.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                id = listDecrypt.get(newValue);
                Crypto CR = (new CryptoFactory()).Activity();
                Map<String,byte[]> rs = new HashMap<String, byte[]>();
                rs = DB.get_by_id(id);

                Title.setText(CR.Decrypt(rs.get("title")));
                HTMLText.setHtmlText(CR.Decrypt(rs.get("text")));
            }
        });



        ListT.setItems(langs);

        Crypto CR = (new CryptoFactory()).Activity();

        listEncrypt = DB.get();

        for(Map.Entry<byte[], Integer> item : listEncrypt.entrySet()){
            listDecrypt.put(CR.Decrypt(item.getKey()), item.getValue());
            langs.add(new String ( CR.Decrypt(item.getKey()) ));
        }
    }




    @FXML
    private void update(ActionEvent event) {
        Map<String,byte[]> rs = new HashMap<String, byte[]>();
        Crypto CR = (new CryptoFactory()).Activity();

        rs.put("text", CR.Encrypt(HTMLText.getHtmlText()));
        rs.put("title", CR.Encrypt(Title.getText()));

        DB.update_by_id(rs,id);

    }

    

}
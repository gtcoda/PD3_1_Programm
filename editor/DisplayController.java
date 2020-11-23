package editor;


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


public class DisplayController extends Controller {

    @FXML
    ListView ListT;
    @FXML
    WebView WView;
    @FXML
    Label Title;



    Map<byte[], Integer> listEncrypt = new HashMap<byte[], Integer>();
    Map<String, Integer> listDecrypt = new HashMap<String, Integer>();

    public ObservableList<String> langs = FXCollections.observableArrayList();

    ///////////
    DataBase DB = DataBase.getInstance("");


    @Override
    public void initialize(URL location, ResourceBundle resources){


        MultipleSelectionModel<String> langsSelectionModel = ListT.getSelectionModel();

        ListT.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){

                Crypto CR = (new CryptoFactory()).Activity();
                int id = listDecrypt.get(newValue);

                Map<String,byte[]> rs;
                rs = DB.get_by_id(id);

                Title.setText(CR.Decrypt(rs.get("title")));

                String text = new String (CR.Decrypt(rs.get("text")));
                WView.getEngine().loadContent(text);

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


    @Override
    public void edit_edit(ActionEvent event) throws Exception {
        SM.setScene("Edit.fxml");
    }


}
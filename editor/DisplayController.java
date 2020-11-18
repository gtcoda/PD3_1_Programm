package editor;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.web.*;
import java.util.*;

import  javafx.fxml.Initializable;
import java.net.URL;


import javafx.geometry.Orientation;
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



    Map<String, Integer> list = new HashMap<String, Integer>();


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
                int id = list.get(newValue);
                Map<String,String> rs;
                rs = DB.get_by_id(id);

                Title.setText(rs.get("title"));
                WView.getEngine().loadContent(rs.get("text"));
            }
        });

        ListT.setItems(langs);

        list = DB.get();

        for(Map.Entry<String, Integer> item : list.entrySet()){
            langs.add(item.getKey());
        }

    }

    @Override
    public void edit_edit(ActionEvent event) throws Exception {
        SM.setScene("Edit.fxml");
    }

}
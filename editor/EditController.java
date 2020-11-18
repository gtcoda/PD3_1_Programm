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
                id = list.get(newValue);
                Map<String,String> rs;
                rs = DB.get_by_id(id);
                Title.setText(rs.get("title"));
                HTMLText.setHtmlText(rs.get("text"));
            }
        });



        ListT.setItems(langs);



        list = DB.get();

        for(Map.Entry<String, Integer> item : list.entrySet()){
            langs.add(item.getKey());
        }
        HTMLText.setHtmlText("Initialize");
    }




    @FXML
    private void update(ActionEvent event) {
        Map<String,String> rs = new HashMap<String, String>();

        rs.put("text",HTMLText.getHtmlText());
        rs.put("title",Title.getText());

        DB.update_by_id(rs,id);

    }

    

}
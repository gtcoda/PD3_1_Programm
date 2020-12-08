package editor.controller;


import editor.*;
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



    Map<String, Integer> AllTitle = new HashMap<String, Integer>();
    Notes NT = Notes.getInstance();
    public ObservableList<String> langs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        MultipleSelectionModel<String> langsSelectionModel = ListT.getSelectionModel();

        ListT.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                // Получим выбраную запись
                Note note = NT.getNote(AllTitle.get(newValue));

                Title.setText(note.title_decrypt);
                WView.getEngine().loadContent(note.text_decrypt);
            }
        });


        AllTitle =  NT.getAllTitle();
        ListT.setItems(langs);
        for(Map.Entry<String, Integer> item : AllTitle.entrySet()){
            langs.add(item.getKey());
        }

    }


    @Override
    public void edit_edit(ActionEvent event) throws Exception {
        SceneManager SM = SceneManager.getInstance();

        SM.setScene(SceneManager.AvabilityScene.Edit);
    }


}
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


public class AddController extends Controller {
    @FXML
    public HTMLEditor HTMLText;
    @FXML
    TextField Title;


    ///////////
    DataBase DB = DataBase.getInstance("");

    @FXML
    private void add(ActionEvent event) {
        Map<String,String> rs = new HashMap<String, String>();
        rs.put("text",HTMLText.getHtmlText());
        rs.put("title",Title.getText());
        DB.insert(rs);
    }

}
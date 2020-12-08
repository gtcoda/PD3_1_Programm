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

/*
* Суперкласс для каждого контроллера
* */


public class Controller implements Initializable {

    SceneManager SM = SceneManager.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    @FXML
    public void edit_edit(ActionEvent event) throws Exception {
        SM.setScene(SceneManager.AvabilityScene.Edit);
    }

    @FXML
    public void edit_delete(ActionEvent event) {

    }

    @FXML
    public void edit_add(ActionEvent event) throws Exception {
        SM.setModalTitle("Добавить.");
        SM.setModalWindows(SceneManager.AvabilityScene.Add);
    }
}

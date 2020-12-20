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


public class EditController extends Controller {
    @FXML
    public HTMLEditor HTMLText;
    @FXML
    TextField Title;
    @FXML
    Button Action;

    ///////////
    DataBase DB = DataBase.getInstance();
    Notes NT = Notes.getInstance();
    Edit E = Edit.getInstanse();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(E.Note == null){
            Action.setText("Add");
        }
        else{
            Action.setText("Update");
            Title.setText(E.Note.title_decrypt);
            HTMLText.setHtmlText(E.Note.text_decrypt);
        }
    }


    @FXML
    private void Action(ActionEvent event) throws Exception{

        if(E.Note == null){
            Note newNote = new Note();
            newNote.addTitle(Title.getText());
            newNote.addText(HTMLText.getHtmlText());

            NT.addNote(newNote);
        }
        else{
           E.Note.title_decrypt = Title.getText();
           E.Note.text_decrypt = HTMLText.getHtmlText();
           NT.updateNote(E.Note);
        }
        // Закроем модальное окно
        SceneManager SM = SceneManager.getInstance();
        SM.hideModalWindows();
    }



}
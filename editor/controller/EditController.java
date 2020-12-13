package editor.controller;

import editor.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.*;
import java.util.*;

import java.net.URL;


public class EditController extends Controller {
    @FXML
    private HTMLEditor HTMLText;
    @FXML
    private TextField Title;
    @FXML
    private Button Action;

    ///////////
    private DataBase DB = DataBase.getInstance();
    private Notes NT = Notes.getInstance();
    private Edit E = Edit.getInstanse();


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
            newNote.setTitle(Title.getText());
            newNote.setText(HTMLText.getHtmlText());

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
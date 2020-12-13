package editor.controller;

import editor.Controller;
import editor.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.scene.*;

import java.io.*;

public class TextEditorController extends Controller {

    @FXML
    HTMLEditor edit;

    public void Open() throws  Exception {

         SceneManager CM = SceneManager.getInstance();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter xlsxfilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.txt");
        fileChooser.getExtensionFilters().add(xlsxfilter);
        fileChooser.getExtensionFilters().addAll(xlsxfilter);
        fileChooser.setTitle("Выбор файла");

        File file = fileChooser.showOpenDialog(CM.getActiveStage());
        if (file != null) {
            String current = "";


            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    current += line;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }




            edit.setHtmlText(current);
        }

    }
    public void Save() throws Exception{
        SceneManager CM = SceneManager.getInstance();

        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.txt");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(CM.getActiveStage());

        try(FileWriter writer = new FileWriter(file))
        {
            // запись всей строки
            String text = edit.getHtmlText();
            writer.write(text);
            writer.flush();
        }

    }

}

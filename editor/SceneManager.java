package editor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.web.HTMLEditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class SceneManager {

    private static SceneManager instance = new SceneManager();

    static Scene ActiveScene = null;
    static boolean newScene = false;
    Stage stage = null;
    public Stage modalWindow = null;

    String ModalTitle = "";


    private SceneManager() {

    }

    public static SceneManager getInstance() {
        return instance;
    }

    public void setStage(Stage st){
        stage = st;
    }

    public void setScene(String scene) throws Exception{
        if (stage != null) {
            Group group = new Group();
            Parent content = FXMLLoader.load(getClass().getResource(scene));
            BorderPane root = new BorderPane();
            root.setCenter(content);

            group.getChildren().add(root);

            ActiveScene = new Scene(group);
            newScene = true;

            stage.setScene(ActiveScene);
            stage.show();
        }
        else {
            System.out.println("Установка сцены без stage!");
        }
    }

    public void setHeight(int height){
        stage.setHeight(height);
    }

    public void setWidth(int width){
        stage.setWidth(width);
    }

    public void setTitle(String title){
        stage.setTitle(title);
    }

    public void setModalTitle(String title){
        ModalTitle = title;
    }

    public void hideModalWindows() throws Exception{
        if(modalWindow != null){
            modalWindow.close();
        }
    }

    public void setModalWindows(String scene) throws Exception{

            Group group = new Group();
            Parent content = FXMLLoader.load(getClass().getResource(scene));
            BorderPane root = new BorderPane();
            root.setCenter(content);

            group.getChildren().add(root);

            Scene ModalScene = new Scene(group);

            // New window (Stage)
            modalWindow = new Stage();
            modalWindow.setTitle(ModalTitle);
            modalWindow.setScene(ModalScene);

            // Specifies the modality for new window.
            modalWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            modalWindow.initOwner(stage);

            // Set position of second window, related to primary window.
            modalWindow.setX(stage.getX() + 200);
            modalWindow.setY(stage.getY() + 100);

            modalWindow.show();

    }


    public Scene getActiveScene(){
        return ActiveScene;
    }

    public Stage getActiveStage(){
        return stage;
    }

}

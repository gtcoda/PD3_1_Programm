package editor;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class SceneManager {
// Доступные сцены
    public enum AvScene {
        Crypts,
        Display,
        Edit,
        KeyRequest,
        TextEditor
    }


    private String path = "fxml/";

    private static SceneManager instance = new SceneManager();

    private Scene ActiveScene = null;
    private boolean newScene = false;
    private Stage stage = null;
    private Stage modalWindow = null;
    private String ModalTitle = "";


    private SceneManager() {   }

    private String ScenePath(AvScene sc){
        String r_path = path;
        switch (sc){
            case Crypts:
                r_path += "Crypts";
                break;
            case Display:
                r_path += "Display";
                break;
            case Edit:
                r_path += "Edit";
                break;
            case KeyRequest:
                r_path += "KeyRequest";
                break;
            case TextEditor:
                r_path += "TextEditor";
                break;


        }
        return r_path + ".fxml";
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public void setStage(Stage st){
        stage = st;
    }


    public void setScene(AvScene sc) throws Exception{
        if (stage != null) {
            Group group = new Group();
            Parent content = FXMLLoader.load(getClass().getResource(ScenePath(sc)));
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


    public void setModalWindows(AvScene sc) throws Exception{

        Group group = new Group();
        Parent content = FXMLLoader.load(getClass().getResource(ScenePath(sc)));
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

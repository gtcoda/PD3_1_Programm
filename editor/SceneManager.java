package editor;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Modality;

import java.io.IOException;

public class SceneManager {
// Доступные сцены
    public enum AvabilityScene{
        Crypts,
        Display,
        Edit,
        KeyRequest,
        TextEditor,
        Protected,
        ProtectedOK
    }

    private String path = "fxml/";

    private static SceneManager instance = new SceneManager();

    static Scene ActiveScene = null;
    static boolean newScene = false;
    Stage stage = null;
    public Stage modalWindow = null;

    String ModalTitle = "";

    private SceneManager() {   }

    private String ScenePath(AvabilityScene sc){
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
            case Protected:
                r_path += "Protected";
                break;
            case ProtectedOK:
                r_path += "ProteectedOK";
                break;
            default:
                throw new IllegalArgumentException("Неверный тип:" + sc);
        }
        return r_path + ".fxml";
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public void setStage(Stage st){
        if(st != null) {
            stage = st;
        }
        else{
            throw new IllegalArgumentException("Передан неверный обьект!");
        }
    }

    public void setScene(AvabilityScene sc) {

        if (stage != null) {
            Group group = new Group();
            try {
            Parent content = FXMLLoader.load(getClass().getResource(ScenePath(sc)));

            BorderPane root = new BorderPane();
            root.setCenter(content);

            group.getChildren().add(root);

            ActiveScene = new Scene(group);
            newScene = true;

            stage.setScene(ActiveScene);
            stage.show();

            } catch (IOException ex) {
                System.out.println("IOException");
                ex.printStackTrace();
            }
            catch(RuntimeException ex) {
                System.out.println("RuntimeException");
                ex.printStackTrace();
            }
            catch(Exception ex) {
                System.out.println("Exception");
                ex.printStackTrace();
            }

        }
        else {
            System.out.println("Установка сцены без stage!");
        }
    }

    public void setHeight(int height){
        if(stage != null) {
            stage.setHeight(height);
        }
    }

    public void setWidth(int width){
        if (stage != null) {
            stage.setWidth(width);
        }
    }

    public void setTitle(String title){
        if (stage != null){
            stage.setTitle(title);
        }
    }

    public void setModalTitle(String title){
        ModalTitle = title;
    }

    public void hideModalWindows() throws Exception{
        if(modalWindow != null){
            modalWindow.close();
        }
    }

    public void setModalWindows(AvabilityScene sc) throws Exception{

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

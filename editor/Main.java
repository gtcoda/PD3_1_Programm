package editor;
import com.beust.jcommander.JCommander;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;

import java.util.List;

public class Main extends Application{

    public String[] arg;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        DataBase DB = DataBase.getInstance();
        Scene scene;

        SceneManager SM = SceneManager.getInstance();
        SM.setStage(stage);

        SM.setScene(SceneManager.AvScene.KeyRequest);


    }






}
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


 ///////////////// Для 9 и 10 лаб работы ОИТ
        CliClipper CC = new CliClipper();

        byte[] pr = {1,2,3,4,5,6,7,8,9,0,};

        CC.CruptPR(pr);



        // получаем переданные параметры
        Application.Parameters params = getParameters();

        List<String> unnamedParams = getParameters().getUnnamed();



        CommandLine cli = new CommandLine();
        JCommander cmdr = new JCommander(cli, unnamedParams.toArray(new String[0]));

        if (cli.help)
        {
            cmdr.usage();
        }

       // CliClipper CC = new CliClipper();
       // CC.DecryptFile("C://work/22.txt","C://work/33.txt","123");
        // CC.DecryptFile("C://work/Input.txt","C://work/Output.txt","123");




/////////////////////////////////////////////////////////////////////////////

        DataBase DB = DataBase.getInstance();
        Scene scene;

        SceneManager SM = SceneManager.getInstance();
        SM.setStage(stage);
        //SM.setScene("TextEditor");

        SM.setScene(SceneManager.AvScene.KeyRequest);

    }






}
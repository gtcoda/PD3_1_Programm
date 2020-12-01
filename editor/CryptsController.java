package editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class CryptsController  extends Controller {

    @FXML
    TextField Input;
    @FXML
    TextField Output;
    @FXML
    TextField Key;

    CliClipper CC = new CliClipper();

    @FXML
    private void Crypt(ActionEvent event) throws Exception{



        CC.EncryptFile(Input.getText(), Output.getText(), Key.getText());

    }

    @FXML
    private void Decrypt(ActionEvent event) throws Exception{
        CC.DecryptFile(Input.getText(), Output.getText(), Key.getText());
    }

}

package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Controller(){
    }

    @FXML
    private void acaoGerente() throws Exception{
        Main.trocaTela("menuGerente.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

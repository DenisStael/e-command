package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label labelPadrao;


    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtSenha;

    @FXML
    private Button botaoCliente;

    @FXML
    private Button botaoGerente;

    @FXML
    private Button botaoCozinheiro;

    @FXML
    private Button botaoGarcom;

    public Controller() {
    }


    @FXML

    private void acaoDobotao(ActionEvent event) {


        labelPadrao.setText("Digite usuario: " + txtUsuario.getText());

    }

    private void acaoGerente(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

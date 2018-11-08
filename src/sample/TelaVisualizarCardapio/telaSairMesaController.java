package sample.TelaVisualizarCardapio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class telaSairMesaController {
    @FXML
    private PasswordField senhaSair;
    @FXML
    private Label labelSair;
    public void acaoConfirmar(ActionEvent actionEvent) throws IOException {
        if(!senhaSair.getText().isEmpty() && senhaSair.getText().equals("sair")){
            Main.trocaTela("TelaMesa/telaMesa.fxml");
        }else{
            labelSair.setText("Digite a senha corretamente!");
        }

    }
}

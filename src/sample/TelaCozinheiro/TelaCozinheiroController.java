package sample.TelaCozinheiro;

import javafx.event.ActionEvent;
import sample.Main;
import java.io.IOException;

public class TelaCozinheiroController {

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void acaoVisualizarPedidos(ActionEvent actionEvent) {
    }
}

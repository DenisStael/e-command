package sample.TelaMontarPrato;

import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class TelaMontarPratoController {
    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }
}

package sample.TelaMesa;

import sample.Main;
import java.io.IOException;

public class TelaMesaController {
    public void acaoVisualizarCardapio() throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");

    }

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }
}

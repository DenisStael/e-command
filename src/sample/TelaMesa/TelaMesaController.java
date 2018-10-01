package sample.TelaMesa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Main;
import sample.TelaPedido.TelaPedidoController;

import java.io.IOException;

public class TelaMesaController {

    @FXML
    private TextField txtNumeroMesa;

    public void acaoVisualizarCardapio() throws IOException {
        if(!txtNumeroMesa.getText().isEmpty()){
            TelaPedidoController.numeroMesa = Integer.parseInt(txtNumeroMesa.getText());
            Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
        }
    }

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaInicial/telaInicial.fxml");
    }
}
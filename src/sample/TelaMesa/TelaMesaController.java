package sample.TelaMesa;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Main;
import sample.TelaPedido.TelaPedidoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public void initialize(URL location, ResourceBundle resources) {
        txtNumeroMesa.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    try {
                        acaoVisualizarCardapio();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}

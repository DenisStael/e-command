package sample.TelaPedido;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import sample.Main;

import java.io.IOException;

public class TelaPedidoController {

    @FXML
    private ListView<?> listaComplementos;

    @FXML
    private TitledPane listaBebidas;

    @FXML
    private ListView<?> listaPratos;

    @FXML
    private TitledPane listaOpcionais;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAddPedido;

    @FXML
    void acaoVoltar(ActionEvent event) throws IOException {
        Main.trocaTela("TelaMesa/telaMesa.fxml");

    }

}

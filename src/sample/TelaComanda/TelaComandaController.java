package sample.TelaComanda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.Pedido;
import sample.TabelaLista;
import sample.TelaInfoPedido.TelaInfoPedidoController;
import sample.TelaPedido.TelaPedidoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaComandaController implements Initializable {
    private TabelaLista tabelaPedido = new TabelaLista();
    @FXML
    private TableView<Pedido> tabelaPedidos;
    @FXML
    private TableColumn colunaCodPedido, colunaNumeroMesa, colunaPrecoPedido;
    @FXML
    private Label labelPreco;
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    @FXML
    private void acaoInformacaoPedido() throws IOException {
        if(tabelaPedidos.getSelectionModel().getSelectedItem() != null){
            TelaInfoPedidoController.codPedido = tabelaPedidos.getSelectionModel().getSelectedItem().getCodpedido();
            Stage stage = new Stage();
            stage.setTitle("Informações do Pedido");
            Parent root = FXMLLoader.load(getClass().getResource("../TelaInfoPedido/telaInfoPedido.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private float calculaPreco(){
        Float precoTotal = 0f;
        for(int i = 0; i < tabelaPedidos.getItems().size(); i++){
            precoTotal += tabelaPedidos.getItems().get(i).getPreco();
        }
        return precoTotal;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "select codpedido, mesa_idmesa, precototal from pedido where mesa_idmesa = "+TelaPedidoController.numeroMesa;
        tabelaPedido.mostraTabelaPedido(tabelaPedidos,colunaCodPedido,colunaNumeroMesa,colunaPrecoPedido,sql);
        labelPreco.setText(Float.toString(calculaPreco()));
    }
}

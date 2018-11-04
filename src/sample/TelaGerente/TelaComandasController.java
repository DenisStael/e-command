package sample.TelaGerente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ConexaoBanco;
import sample.Main;
import sample.Pedido;
import sample.TabelaLista;
import sample.TelaInfoPedido.TelaInfoPedidoController;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TelaComandasController implements Initializable {

    private String sql = "select codpedido, mesa_idmesa, precototal from pedido where statuspedido = 'Aberto'";
    private TabelaLista tabelaPedido= new TabelaLista();
    @FXML
    private TableView<Pedido> tabelaPedidos;
    @FXML
    private TableColumn colunaCodPedido, colunaNumeroMesa,colunaPrecoPedido;
    @FXML
    private Label labelValorTotal;
    @FXML
    private TextField txtNumeroMesa;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoPesquisar(){

    }

    @FXML
    private void acaoInformacaoPedido() throws IOException {

    }

    public void acaoFecharPedido(){

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

    }
}

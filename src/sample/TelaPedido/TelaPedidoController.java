package sample.TelaPedido;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConexaoBanco;
import sample.Main;
import sample.Pedido;
import sample.Prato;
import sample.TelaVisualizarCardapio.TelaVisualizarCardapioController;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TelaPedidoController implements Initializable {

    public static int numeroMesa;
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private static Pedido pedido = new Pedido();
    @FXML
    private TextArea txtObservacao;
    @FXML
    private TableView<Prato> tabelaPedido;
    @FXML
    private TableColumn colunaNome, colunaPreco;
    @FXML
    private Label labelPrecoTotal, labelNumeroMesa;

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        TelaPedidoController.pedido = pedido;
    }

    public void acaoVoltar() throws IOException {
        TelaVisualizarCardapioController.setPedido(pedido);
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    public void acaoConfirmar(){
        /*try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private float calculaPreco(){
        Float precoTotal = 0f;
        for(int i = 0; i < pedido.getListaPedido().size(); i++){
            precoTotal += Float.parseFloat(pedido.getListaPedido().get(i).getPreco());
        }
        return precoTotal;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaPedido.setItems(pedido.getListaPedido());
        labelNumeroMesa.setText(Integer.toString(numeroMesa));
        labelPrecoTotal.setText(Float.toString(calculaPreco()));
    }
}

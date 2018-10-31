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
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
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
        String sql;
        if(!txtNumeroMesa.getText().isEmpty())
            sql = "select codpedido, mesa_idmesa, precototal from pedido where statuspedido = 'Aberto' and mesa_idmesa = "+txtNumeroMesa.getText()+";";
        else
            sql = "select codpedido, mesa_idmesa, precototal from pedido where statuspedido = 'Aberto'";

        tabelaPedido.mostraTabelaPedido(tabelaPedidos,colunaCodPedido,colunaNumeroMesa,colunaPrecoPedido,sql);
        labelValorTotal.setText(Float.toString(calculaPreco()));
        txtNumeroMesa.clear();
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

    public void acaoFecharPedido(){
        try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("update pedido set statuspedido = 'Fechado' where codpedido = ?;");
            ps.setInt(1,tabelaPedidos.getSelectionModel().getSelectedItem().getCodpedido());
            ps.executeUpdate();
            tabelaPedido.mostraTabelaPedido(tabelaPedidos,colunaCodPedido,colunaNumeroMesa,colunaPrecoPedido,sql);
        } catch (SQLException e) {
            e.printStackTrace();
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
        tabelaPedido.mostraTabelaPedido(tabelaPedidos,colunaCodPedido,colunaNumeroMesa,colunaPrecoPedido,sql);
        labelValorTotal.setText(Float.toString(calculaPreco()));
    }
}

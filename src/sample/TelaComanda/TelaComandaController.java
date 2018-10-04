package sample.TelaComanda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.Pedido;
import sample.Prato;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaComandaController implements Initializable {
    private String sql = "";
    private String sql_2 = "";
    private Pedido tabelaPedido;
    private Prato tabelaPrato;
    @FXML
    private TableView<Pedido> tabelaPedidos, tabelaPratos;
    @FXML
    private TableColumn colunaCodPedido, colunaNumeroMesa, colunaPrecoPedido, colunaPrato, colunaDescPrato,
    colunaCodPrato, colunaPrecoPrato;


    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    @FXML
    private void acaoInformacaoPedido() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Informações do Pedido");
        Parent root = FXMLLoader.load(getClass().getResource("telaInformacaoPedido.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tabelaPedido.mostraTabelaPedido(tabelaPedidos,colunaCodPedido,colunaNumeroMesa,colunaPrecoPedido,sql);
    }
}

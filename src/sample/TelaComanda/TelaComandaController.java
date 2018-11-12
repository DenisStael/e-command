package sample.TelaComanda;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.*;
import sample.TelaInfoPedido.TelaInfoPedidoController;
import sample.TelaPedido.TelaPedidoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaComandaController implements Initializable {

    public static Stage stage;
    public static int numeroComanda;
    private TabelaLista tabelaPedido = new TabelaLista();
    @FXML
    private TableView<Pedido> tabelaPedidos;
    @FXML
    private TableColumn colunaCodPedido, colunaNumeroMesa, colunaPrecoPedido;
    @FXML
    private Label labelPreco;
    @FXML
    private Label labelNumeroComanda;
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    @FXML
    private void acaoInformacaoPedido() throws IOException {
        if(tabelaPedidos.getSelectionModel().getSelectedItem() != null){
            TelaInfoPedidoController.codPedido = tabelaPedidos.getSelectionModel().getSelectedItem().getCodpedido();
            Stage stage = new Stage();
            stage.setTitle("Informações do Pedido");
            Parent root = Main.novaTela("TelaInfoPedido/telaInfoPedido.fxml");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void acaoFecharConta() throws IOException {
        TelaPagamentoController.precoTotal = labelPreco.getText();
        Image icone = new Image("sample/img/icone.png");
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("telaPagamento.fxml"));
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Main.stage);
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();
        stage.getIcons().add(icone);
        stage.setTitle("Método de pagamento:");
        stage.setScene(scene);
        stage.show();
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
        String sql = "select pe.codpedido, c.id_mesa, pe.precototal from pedido pe, comanda c where c.id_mesa =" +
                " "+TelaPedidoController.numeroMesa+" and pe.codcomanda = c.codcomanda and pe.statuspedido = 'Aberto';";
        tabelaPedido.mostraTabelaPedido(tabelaPedidos,colunaCodPedido,colunaNumeroMesa,colunaPrecoPedido,sql);
        labelPreco.setText(FormataPreco.formatarFloat(calculaPreco()));
        labelNumeroComanda.setText(String.valueOf(numeroComanda));
    }
}

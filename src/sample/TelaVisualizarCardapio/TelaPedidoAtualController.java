package sample.TelaVisualizarCardapio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import sample.TabelaLista;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPedidoAtualController implements Initializable {

    private TabelaLista tabelaLista = new TabelaLista();
    private static ObservableList<GridPane> listaPedido = FXCollections.observableArrayList();
    @FXML
    private ListView<GridPane> listaPedidoAtual;
    @FXML
    private Label precoTotal;

    public void pegaInformacoes(String nome, String preco, String descricao, int cod){
        GridPane gridPane = tabelaLista.criaGridPane(nome, preco, descricao, cod);
        listaPedido.add(gridPane);
    }

    private float calculaPreco(){
        Float precoTotal = 0f;
        for(int i = 0; i < listaPedido.size(); i++){
            precoTotal += Float.parseFloat(((Label)listaPedido.get(i).getChildren().get(4)).getText());
        }
        return precoTotal;
    }

    public void removerPratoPedido(){
        if(listaPedidoAtual.getSelectionModel().getSelectedItem() != null){
            listaPedido.remove(listaPedidoAtual.getSelectionModel().getSelectedItem());
            precoTotal.setText(Float.toString(calculaPreco()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaPedidoAtual.setItems(listaPedido);
        precoTotal.setText(Float.toString(calculaPreco()));
    }
}

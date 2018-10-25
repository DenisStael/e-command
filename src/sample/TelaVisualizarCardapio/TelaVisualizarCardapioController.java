package sample.TelaVisualizarCardapio;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;
import sample.TelaPedido.TelaPedidoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaVisualizarCardapioController implements Initializable {

    private static Pedido pedido = new Pedido();
    private Prato tabelas = new Prato();
    private String sqlPrato = "select nome,codprato,descricao,preco from prato where cardapio = TRUE and tipo = 'Comida' order by codprato;"; //String sql
    private String sqlBebida = "select nome,codprato,descricao,preco from prato where cardapio = TRUE and tipo = 'Bebida' order by codprato;"; //String sql
    @FXML
    private TableView<Prato> tabelaPratoCardapio, tabelaBebidaCardapio, tabelaPedido;
    @FXML
    private TableColumn colunaNomePrato,colunaPrecoPrato,colunaNomePedido,colunaNomeBebida,colunaPrecoBebida,colunaPrecoPedido;

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        TelaVisualizarCardapioController.pedido = pedido;
    }

    public void acaoVoltar() throws IOException {
        tabelaPedido.getItems().clear();
        Main.trocaTela("TelaMesa/telaMesa.fxml");
    }

    public void acaoComanda() throws IOException {
        Main.trocaTela("TelaComanda/telaComanda.fxml");
    }

    @FXML
    private void acaoSelecaoPrato(){
        if(tabelaBebidaCardapio.getSelectionModel().getSelectedItem() != null)
            tabelaBebidaCardapio.getSelectionModel().clearSelection();
    }

    @FXML
    private void acaoSelecaoBebida(){
        if(tabelaPratoCardapio.getSelectionModel().getSelectedItem() != null)
            tabelaPratoCardapio.getSelectionModel().clearSelection();
    }

    public void acaoAddPedido() {
        if(tabelaPratoCardapio.getSelectionModel().getSelectedItem() != null){
            pedido.getListaPedido().add(tabelaPratoCardapio.getSelectionModel().getSelectedItem());
            tabelaPedido.setItems(pedido.getListaPedido());
        } else if(tabelaBebidaCardapio.getSelectionModel().getSelectedItem() != null){
            pedido.getListaPedido().add(tabelaBebidaCardapio.getSelectionModel().getSelectedItem());
            tabelaPedido.setItems(pedido.getListaPedido());
        }
    }

    public void removerPratoPedido() {
        if(tabelaPedido.getSelectionModel().getSelectedItem() != null){
            pedido.getListaPedido().remove(tabelaPedido.getSelectionModel().getSelectedItem());
        }
    }

    public void acaoProximo() throws IOException {
        TelaPedidoController.setPedido(pedido);
        Main.trocaTela("TelaPedido/telaPedido.fxml");
    }

    public void initialize(URL url, ResourceBundle rb){
        tabelas.mostraTabelaPrato(tabelaPratoCardapio,colunaNomePrato,colunaPrecoPrato,sqlPrato);
        tabelas.mostraTabelaPrato(tabelaBebidaCardapio,colunaNomeBebida,colunaPrecoBebida,sqlBebida);
        colunaNomePedido.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPrecoPedido.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaPedido.setItems(pedido.getListaPedido());
    }
}
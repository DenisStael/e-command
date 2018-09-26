package sample.TelaCardapioBebidas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCardapioBebidasController implements Initializable {

    private String sql = "select p.nome,p.descricao,b.preco,b.codbebida from bebida b, produto p where b.codbebida = p.codproduto and b.cardapio = TRUE;"; //String sql
    private TabelaBebida tabelaBebida = new TabelaBebida();
    @FXML
    TableView<Bebida> tabelaBebidaCardapio;
    @FXML
    TableColumn colunaNomeBebida,colunaDescricaoBebida,colunaPrecoBebida;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    public void acaoAddPedido() {
        System.out.println(tabelaBebidaCardapio.getSelectionModel().getSelectedItem().getCodbebida());
    }

    public void acaoMeuPedido() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaBebida.mostraTabelaBebida(tabelaBebidaCardapio,colunaNomeBebida,colunaDescricaoBebida,colunaPrecoBebida,sql);
    }
}

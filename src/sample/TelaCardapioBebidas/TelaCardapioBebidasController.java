package sample.TelaCardapioBebidas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCardapioBebidasController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();
    TabelaPrato tabelaPrato = new TabelaPrato();
    private String sql = "select p.nome,p.descricao,b.preco from bebida b, produto p where b.codbebida = p.codproduto and b.cardapio = TRUE;"; //String sql
    private TabelaBebida tabelaBebida = new TabelaBebida();
    @FXML
    TableView<Bebida> tabelaBebidaCardapio;
    @FXML
    TableColumn colunaNomeBebida,colunaDescricaoBebida,colunaPrecoBebida;

    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    public void acaoAddPedido(ActionEvent actionEvent) {
    }

    public void acaoComplemento(ActionEvent actionEvent) {
    }

    public void acaoMeuPedido(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaBebida.mostraTabelaBebida(tabelaBebidaCardapio,colunaNomeBebida,colunaDescricaoBebida,colunaPrecoBebida,sql);




    }
}

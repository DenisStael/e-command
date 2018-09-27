package sample.TelaVisualizarCardapio;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaVisualizarCardapioController implements Initializable {

    Prato tabelaPrato = new Prato();
    private String sqlPrato = "select nome,codprato,descricao,preco from prato where cardapio = TRUE order by codprato;"; //String sql
    private Bebida tabelaBebida = new Bebida();
    private String sqlBebida = "select p.nome,p.descricao,b.preco,b.codbebida from bebida b, produto p where b.codbebida = p.codproduto and b.cardapio = TRUE;"; //String sql
    @FXML
    TableView<Prato> tabelaPratoCardapio;
    @FXML
    TableColumn colunaNomePrato,colunaPrecoPrato;
    @FXML
    TableView<Bebida> tabelaBebidaCardapio;
    @FXML
    TableColumn colunaNomeBebida,colunaPrecoBebida;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaMesa/telaMesa.fxml");
    }

    public void acaoAddPedido() {
    }

    public void acaoMeuPedido() {
    }

    public void removerPratoPedido() {
    }

    public void acaoProximo() {
    }

    public void initialize(URL url, ResourceBundle rb){
        tabelaPrato.mostraTabelaPrato(tabelaPratoCardapio,colunaNomePrato,colunaPrecoPrato,sqlPrato);
        tabelaBebida.mostraTabelaBebida(tabelaBebidaCardapio,colunaNomeBebida,colunaPrecoBebida,sqlBebida);
    }
}

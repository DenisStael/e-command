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

    private Prato tabelaPrato = new Prato();
    private String sqlPrato = "select nome,codprato,descricao,preco from prato where cardapio = TRUE and tipo = 'Comida' order by codprato;"; //String sql
    private Prato tabelaBebida = new Prato();
    private String sqlBebida = "select nome,codprato,descricao,preco from prato where cardapio = TRUE and tipo = 'Bebida' order by codprato;"; //String sql
    @FXML
    TableView<Prato> tabelaPratoCardapio, tabelaBebidaCardapio;
    @FXML
    TableColumn colunaNomePrato,colunaPrecoPrato;
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
        tabelaBebida.mostraTabelaPrato(tabelaBebidaCardapio,colunaNomeBebida,colunaPrecoBebida,sqlBebida);
    }
}

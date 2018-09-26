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

    TabelaPrato tabelaPrato = new TabelaPrato();
    private String sql = "select nome,codprato,descricao,preco from prato where cardapio = TRUE order by codprato;"; //String sql
    @FXML
    TableView<Prato> tabelaPratoCardapio;
    @FXML
    TableColumn colunaNomePrato,colunaDescricaoPrato,colunaPrecoPrato;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaMesa/telaMesa.fxml");
    }

    public void acaoAddPedido() {
    }

    public void acaoBebidas() throws IOException {
        Main.trocaTela("TelaCardapioBebidas/telaCardapioBebidas.fxml");
    }

    public void acaoMeuPedido() {
    }

    public void initialize(URL url, ResourceBundle rb){
        tabelaPrato.mostraTabelaPrato(tabelaPratoCardapio,colunaNomePrato,colunaDescricaoPrato,colunaPrecoPrato,sql);
    }
}

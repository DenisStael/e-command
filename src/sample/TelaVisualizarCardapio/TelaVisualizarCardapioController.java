package sample.TelaVisualizarCardapio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaVisualizarCardapioController implements Initializable {
    ConexaoBanco conexaoBanco = new ConexaoBanco();
    TabelaProduto tabelaProduto = new TabelaProduto();
    TabelaPrato tabelaPrato = new TabelaPrato();
    private String sql = "select nome,descricao,preco from prato order by codprato;"; //String sql
    @FXML
    TableView<Prato> tabelaPratoCardapio;
    @FXML
    TableColumn colunaNomePrato,colunaDescricaoPrato,colunaPrecoPrato;


    
    public void initialize(URL url, ResourceBundle rb){
        tabelaPrato.mostraTabelaPrato(tabelaPratoCardapio,colunaNomePrato,colunaDescricaoPrato,colunaPrecoPrato,sql);

    }

    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaMesa/telaMesa.fxml");
    }

    public void acaoAddPedido(ActionEvent actionEvent) {
    }

    public void acaoBebidas(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaCardapioBebidas/telaCardapioBebidas.fxml");
    }

    public void acaoMeuPedido(ActionEvent actionEvent) {
    }
}

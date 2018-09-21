package sample.TelaMontarCardapio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaMontarCardapioController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();
    TabelaProduto tabelaProduto = new TabelaProduto();
    TabelaPrato tabelaPrato = new TabelaPrato();
    private String sql = "select * from produto order by codproduto;"; //sql pra consulta
    private String sql2 = "select * from prato order by codprato;"; //String sql
    @FXML
    private TableView<Produto> tabelaProdutos, tabelaPratos;
    @FXML
    private TableColumn<Produto, String> colunaProd, colunaDescProd, colunaPrato, colunaDescPrato;
    @FXML
    private TableColumn<Produto, Integer> colunaCodProd, colunaQtdProd, colunaCodPrato, colunaPreco;

    @FXML
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }
    @FXML
    private void adicionarPrato() {

    }
    @FXML
    private void adicionarBebida(){

    }
    @FXML
    private void adicionarComplemento(){

    }
    @FXML
    private void adicionarOpcional(){

    }
    @FXML
    private void acaoPesquisarProduto(){

    }
    @FXML
    private void acaoPesquisarPrato(){

    }
    @FXML
    private void acaoVisualizarCardapio(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescProd,colunaCodProd,colunaQtdProd,sql);
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sql2);
    }
}

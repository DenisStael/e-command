package sample.TelaEditarPrato;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Main;
import sample.TabelaPrato;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaEditarPratoController implements Initializable {

    TabelaPrato tabelaPrato = new TabelaPrato();
    private String sql = "select * from prato order by codprato;"; //String sql

    @FXML
    private TableView tabelaPratos;
    @FXML
    private TableColumn colunaPratos, colunaDescricao, colunaCodigo, colunaPreco;
    @FXML
    private TextField txtNome, txtPreco, txtCodigo, txtPesquisa;
    @FXML
    private TextArea txtDescricao;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoPesquisar() {
        String sqlPesquisa = "select * from prato where nome ilike '%"+txtPesquisa.getText()+"%';";
        tabelaPrato.mostraTabela(tabelaPratos,colunaPratos,colunaDescricao,colunaCodigo,colunaPreco,sqlPesquisa);
    }

    public void acaoAtualizar(){

    }

    public void acaoRemover(){

    }

    public void acaoLimpar(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaPrato.mostraTabela(tabelaPratos,colunaPratos,colunaDescricao,colunaCodigo,colunaPreco,sql);
    }
}


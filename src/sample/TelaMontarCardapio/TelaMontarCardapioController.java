package sample.TelaMontarCardapio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
    private TextField txtNomePrato,txtNomeProduto,txtCodPrato;

    @FXML
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }
    @FXML
    private void acaoPesquisarProduto(){
        String sqlPesquisa = "select * from produto where nome ilike '%"+txtNomeProduto.getText()+"%';";
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescProd,colunaCodProd,colunaQtdProd,sqlPesquisa);
    }
    @FXML
    private void acaoPesquisarPrato(){
       String sqlPesquisa = "select * from prato where nome ilike '%"+txtNomePrato.getText()+"%';";
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sqlPesquisa);

    }
    @FXML
    private void acaoVisualizarCardapio(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescProd,colunaCodProd,colunaQtdProd,sql);
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sql2);
    }

    public void acaoAdicionarPrato(ActionEvent actionEvent) {
       /* if(!txtCodPrato.getText().isEmpty())
        {
            try {
                //Declaração SQL pra inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement
                        ("ALTER TABLE Prato SET (codprato) VALUES(?);");

                //Atribui os parâmetros e os valores à declaração SQL criada anteriormente
                ps.setString(1, txtCodPrato.getText());

                ps.executeUpdate();//Executa a declaração SQL

                //Mensagem de Sucesso
                JOptionPane.showMessageDialog(null, "Prato Cadastrado!");

                //Chama o método mostraTabela da classe TabelaProduto para mostrar os produtos cadastrados no banco na tabela
                tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sql);
            } catch (Exception e) {
                //Mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato!\nErro: " + e);
            }
        }*/
    }

    public void acaoAdicionarComplemento(ActionEvent actionEvent) {
    }

    public void acaoAdicionarBebida(ActionEvent actionEvent) {
    }

    public void acaoAdicionarOpcional(ActionEvent actionEvent) {
    }
}

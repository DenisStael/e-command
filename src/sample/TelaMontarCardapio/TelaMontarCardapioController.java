package sample.TelaMontarCardapio;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField txtNomePrato,txtNomeProduto,txtCodPrato,txtCodProduto,txtPreco;

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

    public void acaoAdicionarPrato() {
       if(!txtCodPrato.getText().isEmpty())
        {
            try {
                //Declaração SQL pra inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement
                        ("UPDATE Prato SET cardapio = TRUE WHERE codprato = ?;");

                //Atribui os parâmetros e os valores à declaração SQL criada anteriormente
                ps.setInt(1, Integer.parseInt(txtCodPrato.getText()));

                ps.executeUpdate();//Executa a declaração SQL

                limpar(); //limpa os campos de texto
                //Mensagem de Sucesso
                JOptionPane.showMessageDialog(null, "Prato Cadastrado!");

            } catch (Exception e) {
                //Mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato!\nErro: " + e);
            }
        }
    }

    public void adicionarAoCardapio(String SQL) {
        try {
            //Declaração SQL pra inserção no banco
            PreparedStatement ps = conexaoBanco.connection.prepareStatement(SQL);

            //Atribui os parâmetros e os valores à declaração SQL criada anteriormente
            ps.setInt(1, Integer.parseInt(txtCodProduto.getText()));
            ps.setFloat(2, Float.parseFloat(txtPreco.getText()));

            ps.executeUpdate();//Executa a declaração SQL

            limpar(); //limpa os campos de texto
            //Mensagem de Sucesso
            JOptionPane.showMessageDialog(null, "Produto adicionado!");

        } catch (Exception e) {
            //Mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto!\nErro: " + e);
        }
    }

    public void acaoAdicionarComplemento(){
        adicionarAoCardapio("INSERT INTO Complemento(codcomplemento, preco, cardapio) VALUES(?,?,true);");
    }

    public void acaoAdicionarBebida() {
        adicionarAoCardapio("INSERT INTO Bebida(codbebida, preco, cardapio) VALUES(?,?,true);");
    }

    public void acaoAdicionarOpcional() {
        adicionarAoCardapio("INSERT INTO Opcional(codopcional, preco, cardapio) VALUES(?,?,true);");
    }

    public void limpar(){
        txtPreco.clear(); txtCodPrato.clear(); txtCodProduto.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescProd,colunaCodProd,colunaQtdProd,sql);
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sql2);
    }

}

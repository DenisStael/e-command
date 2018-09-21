package sample.TelaGerenciarEstoque;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.ConexaoBanco;
import sample.Main;
import sample.Produto;
import sample.TabelaProduto;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TelaGerenciarEstoqueController implements Initializable {

    private ConexaoBanco conexaoBanco = new ConexaoBanco(); //Objeto de conexão com o banco
    private TabelaProduto tabelaProduto = new TabelaProduto(); //Tabela dos produtos cadastrados no banco
    private String sql = "select nome,codproduto,descricao,quantidade from produto order by codproduto;"; //String sql
    //Atributos da tela
    @FXML
    private TextField txtNomeProduto,txtQtdProduto, txtCodProduto, txtPesquisar; //Caixas de texto
    @FXML
    private TableColumn<Produto, String> colunaProd, colunaDescricao; //Colunas da tabela
    @FXML
    private TableColumn<Produto, Integer> colunaCod, colunaQuantidade; //Colunas da tabela
    @FXML
    private TableView<Produto> tabelaProdutos; //Tabela
    @FXML
    private TextArea txtDescricao; //Caixa de texto

    //Método de voltar para tela anterior
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml"); //Chama a scene
    }

    //Método de limpar os campos da tela
    public void acaoLimpar() {
        txtCodProduto.clear(); txtDescricao.clear();
        txtNomeProduto.clear(); txtQtdProduto.clear();
        txtPesquisar.clear();
    }

    //Método de remover os produtos do banco
    public void acaoRemoverProduto() {
        try {
            int cod = Integer.parseInt(txtCodProduto.getText()); //Recebe o código inserido pelo usuário convertido para inteiro

            //Cria declaração sql
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("DELETE FROM Produto WHERE codproduto = ? ;");
            ps.setInt(1, cod); //Insere valor no parâmetro da declaração sql
            ps.executeUpdate(); //Eexecuta declaração sql

            //Chama método mostraTabela com passagem de parâmetros da tabela, colunas e String sql que será executada
            tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
            JOptionPane.showMessageDialog(null, "Produto Deletado!"); //Mensagem de sucesso
        }
        catch (SQLException e){
            //Mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao Deletar produto!\nErro: " + e);
        }
    }

    //Método de adicionar produtos ao banco
    public void acaoAttProduto() {
            try {
                int cod = Integer.parseInt(txtCodProduto.getText()); //Recebe o código inserido pelo usuário convertido para inteiro

                //Cria declaração sql
                PreparedStatement ps = conexaoBanco.connection.prepareStatement("UPDATE Produto set descricao = ? ,nome = ? ,quantidade = ? WHERE codproduto = ? ;");

                //Insere valores nos parâmetros da declaração sql
                ps.setString(1, txtDescricao.getText());
                ps.setString(2, txtNomeProduto.getText());
                ps.setInt(3, Integer.parseInt(txtQtdProduto.getText()));
                ps.setInt(4, cod);
                ps.executeUpdate(); // Executa a declaração sql

                //Chama método mostraTabela com passagem de parâmetros da tabela, colunas e String sql que será executada
                tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
                JOptionPane.showMessageDialog(null, "Produto Atualizado!");//Mensagem de Sucesso
            }
            catch(SQLException e) {
                //Mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao Atualizar produto!\nErro: " + e);
            }
            acaoLimpar();
    }

    //Método de pesquisar pelos produtos por nome
    public void acaoPesquisar() {
        String sqlPesquisa = "select * from produto where nome ilike '%"+txtPesquisar.getText()+"%';";
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sqlPesquisa);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Chama método mostraTabela com passagem de parâmetros da tabela, colunas e String sql que será executada
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
    }
}

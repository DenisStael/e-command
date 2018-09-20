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

    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private TabelaProduto tabelaProduto = new TabelaProduto();
    private String sql = "select nome,codproduto,descricao,quantidade from produto order by codproduto;";
    @FXML
    private TextField txtNomeProduto,txtQtdProduto, txtCodProduto, txtPesquisar;
    @FXML
    private TableColumn<Produto, String> colunaProd, colunaDescricao;
    @FXML
    private TableColumn<Produto, Integer> colunaCod, colunaQuantidade;
    @FXML
    TableView<Produto> tabelaProdutos;
    @FXML
    TextArea txtDescricao;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoLimpar() {
        txtCodProduto.clear(); txtDescricao.clear();
        txtNomeProduto.clear(); txtQtdProduto.clear();
        txtPesquisar.clear();
    }

    public void acaoRemoverProduto() {
        try {
            int cod = Integer.parseInt(txtCodProduto.getText());
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("DELETE FROM Produto WHERE codproduto = ? ;");
            ps.setInt(1, cod);
            ps.executeUpdate();
            tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
            JOptionPane.showMessageDialog(null, "Produto Deletado!");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Deletar produto!\nErro: " + e);
        }
    }

    public void acaoAttProduto() {
            try {
                int cod = Integer.parseInt(txtCodProduto.getText());
                PreparedStatement ps = conexaoBanco.connection.prepareStatement("UPDATE Produto set descricao = ? ,nome = ? ,quantidade = ? WHERE codproduto = ? ;");

                ps.setString(1, txtDescricao.getText());
                ps.setString(2, txtNomeProduto.getText());
                ps.setInt(3, Integer.parseInt(txtQtdProduto.getText()));
                ps.setInt(4, cod);
                ps.executeUpdate();
                tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
                JOptionPane.showMessageDialog(null, "Produto Atualizado!");
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao Atualizar produto!\nErro: " + e);
            }
            acaoLimpar();
        }

    public void acaoPesquisar() {
        //tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
    }
}

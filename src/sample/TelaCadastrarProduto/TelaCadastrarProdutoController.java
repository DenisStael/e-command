package sample.TelaCadastrarProduto;

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

public class TelaCadastrarProdutoController implements Initializable {

    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private TabelaProduto tabelaProduto = new TabelaProduto();
    private String sql = "select nome,codproduto,descricao,quantidade from produto order by codproduto;";
    @FXML
    private TextField txtQtdProduto, txtNomeProduto;
    @FXML
    private TableColumn<Produto, String> colunaProd, colunaDescricao;
    @FXML
    private TableColumn<Produto, Integer> colunaCod, colunaQuantidade;
    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TextArea txtDescricao;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoCdProduto() {
        if(!txtNomeProduto.getText().isEmpty() && !txtDescricao.getText().isEmpty() && !txtQtdProduto.getText().isEmpty()){
            try {
                PreparedStatement ps = conexaoBanco.connection.prepareStatement
                        ("INSERT INTO Produto(nome, quantidade, descricao) VALUES(?,?,?);");
                ps.setString(1, txtNomeProduto.getText());
                ps.setInt(2, Integer.parseInt(txtQtdProduto.getText()));
                ps.setString(3, txtDescricao.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto Cadastrado!");
                tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!\nErro: " + e);
            }
            txtQtdProduto.clear();
            txtDescricao.clear();
            txtNomeProduto.clear();
        } else JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
    }

    public void acaoCancelar() {
        txtQtdProduto.clear();
        txtDescricao.clear();
        txtNomeProduto.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,sql);
    }
}



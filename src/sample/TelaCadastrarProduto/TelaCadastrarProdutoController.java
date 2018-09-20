package sample.TelaCadastrarProduto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConexaoBanco;
import sample.Main;
import sample.Produto;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TelaCadastrarProdutoController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    @FXML
    private TextField txtQtdProduto, txtNomeProduto;

    @FXML
    private TableColumn<Produto, String> colunaProd, colunaDescricao;
    @FXML
    private TableColumn<Produto, Integer> colunaCod, colunaQuantidade;
    @FXML
    TableView<Produto> tabelaProdutos;
    @FXML
    TextArea txtDescricao;
    ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

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
                mostraTabela();
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


    private void mostraTabela(){
        try {
            listaProdutos.clear();
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select nome,codproduto,descricao,quantidade from produto order by codproduto;");
            while (rs.next()){
                listaProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto"),
                        rs.getString("descricao"), rs.getInt("quantidade")));
            }
            rs.close();
            colunaProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tabelaProdutos.setItems(listaProdutos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar produtos!\n"+e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostraTabela();
    }
}



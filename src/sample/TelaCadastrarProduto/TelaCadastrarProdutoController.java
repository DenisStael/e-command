package sample.TelaCadastrarProduto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sample.ConexaoBanco;
import sample.Main;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastrarProdutoController {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    @FXML
    private TextField txtQtdProduto, txtNomeProduto;

    @FXML
    TextArea txtDescricao;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoCdProduto() {
        try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement
                     ("INSERT INTO Produto(nome, quantidade, descricao) VALUES(?,?,?);");
            ps.setString(1, txtNomeProduto.getText());
            ps.setInt(2, Integer.parseInt(txtQtdProduto.getText()));
            ps.setString(3, txtDescricao.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!\nErro: " + e);
        }
        txtQtdProduto.clear();
        txtDescricao.clear();
        txtNomeProduto.clear();
    }

    public void acaoCancelar(ActionEvent actionEvent) {
        txtQtdProduto.clear();
        txtDescricao.clear();
        txtNomeProduto.clear();
    }

    public void acaoSalvar(ActionEvent actionEvent) {
    }

    public void acaoAttProduto(ActionEvent actionEvent) {
    }

    /*public void acaoRemoverProduto(ActionEvent actionEvent) {
        try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement
                    ("DELETE FROM Produto WHERE codproduto = ? ;");
            ps.setInt(1, txt);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }



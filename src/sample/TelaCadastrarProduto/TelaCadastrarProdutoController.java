package sample.TelaCadastrarProduto;

import javafx.fxml.FXML;
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
    private TextField txtQtdProduto, txtNomeProduto, txtDescricao;

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
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!\nErro: "+e);
        }
        txtQtdProduto.clear();
        txtDescricao.clear();
        txtNomeProduto.clear();
    }

}

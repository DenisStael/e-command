package sample.TelaGerenciarEstoque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.ConexaoBanco;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaGerenciarEstoqueController {
    public void acaoVoltar(ActionEvent actionEvent) {
    }

    public void acaoCancelar(ActionEvent actionEvent) {
    }

    public void acaoSalvar(ActionEvent actionEvent) {
    }

    public void acaoRemoverProduto(ActionEvent actionEvent) {
        try {
             int cod = Integer.parseInt(txtCodProduto.getText());
            PreparedStatement ps = ConexaoBanco.connection.prepareStatement("DELETE FROM Produto WHERE codproduto = ? ;");
            ps.setInt(1, cod);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto Deletado!");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Deletar produto!\nErro: " + e);
        }

    }

    public void acaoAttProduto(ActionEvent actionEvent) {
            try {
                int cod = Integer.parseInt(txtCodProduto.getText());
                PreparedStatement ps = ConexaoBanco.connection.prepareStatement("UPDATE Produto set descricao = ? ,nome = ? ,quantidade = ? WHERE codproduto = ? ;");

                ps.setString(1, txtDescricao.getText());
                ps.setString(2, txtNomeProduto.getText());
                ps.setInt(3, Integer.parseInt(txtQtdProduto.getText()));
                ps.setInt(4, cod);

                JOptionPane.showMessageDialog(null, "Produto Atualizado!");
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao Atualizar produto!\nErro: " + e);
            }
            txtQtdProduto.clear();
            txtDescricao.clear();
            txtNomeProduto.clear();

        }

    @FXML
    private TextField txtNomeProduto,txtQtdProduto, txtCodProduto;

    @FXML
    private TextArea txtDescricao;


    public void acaoPesquisar(ActionEvent actionEvent) {
    }
}

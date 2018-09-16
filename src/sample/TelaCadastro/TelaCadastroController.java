package sample.TelaCadastro;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.ConexaoBanco;
import sample.Main;
import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastroController {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    public static String tipo;
    @FXML
    TextField txtNome;
    @FXML
    PasswordField txtSenha, txtConfirmaSenha;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void acaoEnvioCadastro() {
        try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("insert into usuario(nome,senha)VALUES(?,?)");
            ps.setString(1, "Pedro");
            ps.setString(2, "admin");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir\nErro: "+e);
        }
    }

}

package sample.TelaCadastro;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.ConexaoBanco;
import sample.Main;
import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TelaCadastroController {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    public static String tipo;
    @FXML
    TextField txtNome;
    @FXML
    PasswordField txtSenha, txtConfirmaSenha;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoEnvioCadastro() {
        if(!txtNome.getText().isEmpty() && !txtSenha.getText().isEmpty() && !txtConfirmaSenha.getText().isEmpty()){
            if(txtSenha.getText().equals(txtConfirmaSenha.getText())){
                try {
                    PreparedStatement ps = conexaoBanco.connection.prepareStatement("insert into usuario(nome,senha)VALUES(?,?);");
                    ps.setString(1, txtNome.getText());
                    ps.setString(2, txtSenha.getText());
                    ps.executeUpdate();
                    Statement stmt = conexaoBanco.connection.createStatement();
                    ResultSet rs = stmt.executeQuery("select count(codusuario) qtde from usuario;");
                    int qtde = 0;
                    if(rs.next()) {
                        qtde = rs.getInt("qtde");
                    }
                    rs.close();
                    PreparedStatement ps2;
                    if(tipo.equals("Cozinheiro")){
                        ps2 = conexaoBanco.connection.prepareStatement("insert into cozinheiro(usuario_codusuario) VALUES(?);");
                        ps2.setInt(1, qtde);
                        ps2.executeUpdate();
                    } else if(tipo.equals("Garçom")){
                        ps2 = conexaoBanco.connection.prepareStatement("insert into garcom(usuario_codusuario) VALUES(?);");
                        ps2.setInt(1, qtde);
                        ps2.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir os dados!\nErro: "+e);
                }
                txtNome.clear();
                txtSenha.clear();
                txtConfirmaSenha.clear();
            } else {
                JOptionPane.showMessageDialog(null, "As senhas são diferentes!");
            }
        } else JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
    }

}

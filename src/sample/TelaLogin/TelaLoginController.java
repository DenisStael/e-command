package sample.TelaLogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.ConexaoBanco;
import sample.Main;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    @FXML
    private Label label;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Label labelErroLogin;
    private static String tipo;

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        TelaLoginController.tipo = tipo;
    }

    public void acaoLogar() throws IOException {
        if (tipo == "Gerente") {
            if ((txtUsuario.getText().equals("admin")) && (txtSenha.getText().equals("admin"))) {
                Main.trocaTela("TelaGerente/telaGerente.fxml");
            } else {
                usuarioIncorreto();
            }
        } else if (tipo == "Garçom") {
            try {
                Statement stmt = conexaoBanco.connection.createStatement();
                ResultSet rs = stmt.executeQuery("select u.nome,u.senha from usuario u, garcom g where" +
                        " u.codusuario = g.usuario_codusuario;");
                if(rs.next()){
                    while(rs.next()){
                        if(txtUsuario.getText().equals(rs.getString("nome"))
                                && txtSenha.getText().equals(rs.getString("senha"))){
                            Main.trocaTela("TelaGarcom/telaGarcom.fxml");
                        } else usuarioIncorreto();
                    }
                } else usuarioIncorreto();

                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (tipo == "Cozinheiro") {
            try {
                Statement stmt = conexaoBanco.connection.createStatement();
                ResultSet rs = stmt.executeQuery("select u.nome,u.senha from usuario u, cozinheiro c " +
                        "where u.codusuario = c.usuario_codusuario;");
                if(rs.next()){
                    while(rs.next()){
                        if(txtUsuario.getText().equals(rs.getString("nome"))
                                && txtSenha.getText().equals(rs.getString("senha"))){
                            Main.trocaTela("TelaCozinheiro/telaCozinheiro.fxml");
                        } else usuarioIncorreto();
                    }
                } else usuarioIncorreto();
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro:\n"+e);
            }
        }
    }

    private void usuarioIncorreto(){
        labelErroLogin.setText("Usuário ou senha inválidos, tente novamente");
        txtUsuario.clear();
        txtSenha.clear();
    }

    public void acaoRecuperarSenha() {
        //método não implementado ainda
    }

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaInicial/telaInicial.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(tipo);
    }
}

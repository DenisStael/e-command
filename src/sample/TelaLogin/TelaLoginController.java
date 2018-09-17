package sample.TelaLogin;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Main;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Preloader.*;

public class TelaLoginController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;


    @FXML
    private Label labelErroLogin;
    public static String tipo;

    public void acaoLogar() throws IOException {
        if (tipo == "Gerente") {
            if ((txtUsuario.getText().equals("admin")) && (txtSenha.getText().equals("admin"))) {
                Main.trocaTela("TelaGerente/telaGerente.fxml");
            } else {
                labelErroLogin.setText("Usuário ou senha inválidos, tente novamente");
            }
        } else if (tipo == "Garçom") {
            System.out.println("Métodos ainda não implementados");

        } else if (tipo == "Cozinheiro") {
            System.out.println("Métodos ainda não implementados");
        }
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

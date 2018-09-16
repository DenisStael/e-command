package sample.TelaLogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Main;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

    @FXML
    private Label label;

    public static String tipo;

    public void acaoLogar() throws IOException {
        if(tipo == "Gerente")
            Main.trocaTela("TelaGerente/telaGerente.fxml");
        else
            System.out.println("Métodos ainda não implementados");
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

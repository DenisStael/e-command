package sample.TelaLogin;

import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class TelaLoginController {
    public void acaoLogar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");

    }

    public void acaoRecuperarSenha(ActionEvent actionEvent) {

    }
    public void acaoEmail(ActionEvent actionEvent) {
    }

    public void acaoSenha(ActionEvent actionEvent) {
    }

    public void acaoCadastrar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaCadastro/telaCadastro.fxml");
    }
}

package sample.TelaCadastro;

import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class TelaCadastroController {
    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void acaoEnviarCadastro(ActionEvent actionEvent) {
    }

    public void acaoCdEmail(ActionEvent actionEvent) {
    }

    public void acaoConfirmarCdEmail(ActionEvent actionEvent) {
    }

    public void acaoCriarSenha(ActionEvent actionEvent) {
    }

    public void acaoConfirmarCriarSenha(ActionEvent actionEvent) {
    }
}

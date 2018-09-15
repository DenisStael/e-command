package sample.TelaGerente;

import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class TelaGerenteController {

    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void acaoConfirmarMenu(ActionEvent actionEvent) throws IOException {

    }

    public void acaoMenuGerente(ActionEvent actionEvent) {
    }

    public void acaoCadastrarProduto(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaCadastrarProduto/telaCadastrarProduto.fxml");

    }

    public void acaoMontarCardapio(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaMontarCardapio/telaMontarCardapio.fxml");
    }

    public void acaoMontarPrato(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaMontarPrato/telaMontarPrato.fxml");
    }

    public void acaoEditarPrato(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaEditarPrato/telaEditarPrato.fxml");
    }
}

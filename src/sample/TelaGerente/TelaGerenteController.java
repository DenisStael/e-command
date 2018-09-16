package sample.TelaGerente;

import sample.Main;
import sample.TelaCadastro.TelaCadastroController;

import java.io.IOException;

public class TelaGerenteController {

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void acaoCadastrarCozinheiro() throws IOException {
        TelaCadastroController.tipo = "Cozinheiro";
        Main.trocaTela("TelaCadastro/telaCadastro.fxml");
    }

    public void acaoCadastrarGarcom() throws IOException {
        TelaCadastroController.tipo = "Garçom";
        Main.trocaTela("TelaCadastro/telaCadastro.fxml");
    }

    public void acaoCadastrarProduto() throws IOException {
        Main.trocaTela("TelaCadastrarProduto/telaCadastrarProduto.fxml");
    }

    public void acaoMontarCardapio() throws IOException {
        Main.trocaTela("TelaMontarCardapio/telaMontarCardapio.fxml");
    }

    public void acaoMontarPrato() throws IOException {
        Main.trocaTela("TelaMontarPrato/telaMontarPrato.fxml");
    }

    public void acaoEditarPrato() throws IOException {
        Main.trocaTela("TelaEditarPrato/telaEditarPrato.fxml");
    }
}

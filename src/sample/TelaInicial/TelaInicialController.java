package sample.TelaInicial;

import javafx.fxml.FXML;
import sample.Main;

public class TelaInicialController {

    //Método de ação do botão Gerente, Garcom e Cozinheiro (Tela de Login)
    @FXML
    private void acaoLogin() throws Exception{
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    /*Método de ação do botão Garçom
    @FXML
    private void acaoGarcom() throws Exception{
        Main.trocaTela("telaGarcom.fxml");
    }

    //Método de ação do botão Cozinheiro
    @FXML
    private void acaoCozinheiro() throws Exception{
        Main.trocaTela("telaCozinheiro.fxml");
    }

    //Método de ação do botão Cliente
    @FXML
    private void acaoCliente() throws Exception{
        Main.trocaTela("telaCliente.fxml");
    }*/

}

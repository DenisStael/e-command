package sample.TelaLogout;

import sample.TelaCozinheiro.TelaCozinheiroController;
import sample.TelaGarcom.TelaGarcomController;
import sample.TelaGerente.TelaGerenteController;
import java.io.IOException;

public class TelaLogoutController {

    public static Class classe;

    public void acaoSair() throws IOException {
        if(classe.equals(TelaGerenteController.class))
            TelaGerenteController.sair();
        else if(classe.equals(TelaCozinheiroController.class))
            TelaCozinheiroController.sair();
        else if(classe.equals(TelaGarcomController.class))
            TelaGarcomController.sair();
    }

    public void acaoFicar(){
        if(classe.equals(TelaGerenteController.class))
            TelaGerenteController.ficar();
        else if(classe.equals(TelaCozinheiroController.class))
            TelaCozinheiroController.ficar();
        else if(classe.equals(TelaGarcomController.class))
            TelaGarcomController.ficar();
    }
}

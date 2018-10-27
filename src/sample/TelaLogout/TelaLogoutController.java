package sample.TelaLogout;

import sample.Logout;
import java.io.IOException;

public class TelaLogoutController extends Logout{

    public void acaoSair() throws IOException {
        sair();
    }

    public void acaoFicar(){
        ficar();
    }
}

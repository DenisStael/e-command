package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.TelaLogout.TelaLogoutController;

import java.io.IOException;

public class Logout {

    public static Stage stage = new Stage();

    public void acaoSair() throws IOException {
        TelaLogoutController.classe = getClass();
        Parent root = FXMLLoader.load(getClass().getResource("../TelaLogout/telaLogout.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sair?");
        stage.setScene(scene);
        stage.show();
    }

    public static void sair() throws IOException {
        stage.close();
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public static void ficar(){
        stage.close();
    }
}

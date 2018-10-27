package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Logout {

    private static Stage stage;

    protected void acaoSair() throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../TelaLogout/telaLogout.fxml"));
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Main.stage);
        stage.resizableProperty().setValue(false);
        stage.setTitle("Sair?");
        stage.setScene(scene);
        stage.show();
    }

    protected void sair() throws IOException {
        stage.close();
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    protected void ficar(){
        stage.close();
    }
}

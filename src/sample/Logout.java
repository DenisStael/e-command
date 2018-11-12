package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Logout {

    private static Stage stage;

    public void acaoSair() throws IOException {
        Image icone = new Image(getClass().getResourceAsStream("../img/question.png"));
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../TelaLogout/telaLogout.fxml"));
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Main.stage);
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();
        stage.getIcons().add(icone);
        stage.setTitle("Sair?");
        stage.setScene(scene);
        stage.show();
    }

    public void sair() throws IOException {
        stage.close();
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void ficar(){
        stage.close();
    }
}

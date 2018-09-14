package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static Class thisClass;

    public Main(){
        thisClass = getClass();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("telaInicial.fxml"));
        stage.setTitle("Ecommand");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void trocaTela(String tela) throws IOException {
        Parent root = FXMLLoader.load(thisClass.getResource(tela));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static Stage stage; //Stage principal
    public static Class thisClass; //Atributo Classe

    //Construtor da classe para definir o método getClass()
    public Main(){
        thisClass = getClass();
    }

    //Primeira execucao do Stage (primeira tela)
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial/telaInicial.fxml"));
        stage.setTitle("Ecommand");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /*Método para alterar Scenes utilizando o mesmo Stage
      com passagem de parâmetro de uma string referenciando o nome
      do arquivo .fxml que será carregado.*/

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

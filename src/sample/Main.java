package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.TelaCadastro.TelaCadastroController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    public static Stage stage; //Stage principal
    public static Class thisClass; //Atributo Classe
    ConexaoBanco conexaoBanco = new ConexaoBanco(); //Objeto de conexão com o banco

    //Construtor da classe para definir o método getClass()
    public Main() {
        thisClass = getClass();
    }

    //Primeira execução do Stage (primeira tela)
    @Override
    public void start(Stage stage) throws Exception {
        conexaoBanco.conectaBanco();//Realiza a conexão com o banco de dados
        Parent root;
        Statement stmt = conexaoBanco.connection.createStatement();
        ResultSet rs = stmt.executeQuery("select codusuario from usuario where tipo = any " +
                "(select tipo from usuario where tipo = 'Gerente');");
        if (rs.next()) {
            root = FXMLLoader.load(getClass().getResource("TelaInicial/telaInicial.fxml"));
            this.stage = stage;
            stage.setTitle("Ecommand");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("TelaCadastro/telaCadastro.fxml"));
            TelaCadastroController.tipo = "Gerente";
            this.stage = stage;
            stage.setTitle("Ecommand");
            stage.setScene(new Scene(root));
            stage.show();
        }

        //Chamada do método ao clicar no botão de fechar a tela
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                conexaoBanco.desconectaBanco();//Realiza a desconexão do banco de dados
                stage.close();//Fecha a aplicação
            }
        });
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

package sample.TelaComanda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import sample.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaAvaliacao implements Initializable {

    public static Stage stage;
    private Rating ratingAtendimento = new Rating(), ratingAplicativo = new Rating(), ratingComida = new Rating();

    @FXML
    private Pane pane;

    public void acaoSemAvaliar() throws IOException {
        telaPagamento();
    }

    public void acaoEnviarAvaliacao(){
        System.out.println("Comida: "+ratingComida.getRating()+"\nAtendimento: "+ratingAtendimento.getRating()+"\nAplicativo: "+ratingAplicativo.getRating());
    }

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaComanda/telaComanda.fxml");
    }

    private void telaPagamento() throws IOException {
        Image icone = new Image("sample/img/icone.png");
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("telaPagamento.fxml"));
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Main.stage);
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();
        stage.getIcons().add(icone);
        stage.setTitle("Método de pagamento:");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ratingAtendimento.getStyleClass().add("rating");
        ratingAplicativo.getStyleClass().add("rating");
        ratingComida.getStyleClass().add("rating");
        ratingAtendimento.setLayoutX(260);
        ratingAtendimento.setLayoutY(123);
        ratingAplicativo.setLayoutX(260);
        ratingAplicativo.setLayoutY(185);
        ratingComida.setLayoutX(260);
        ratingComida.setLayoutY(250);
        pane.getChildren().addAll(ratingAtendimento,ratingAplicativo,ratingComida);
    }
}

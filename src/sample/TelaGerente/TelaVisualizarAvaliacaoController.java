package sample.TelaGerente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.Rating;
import sample.Avaliacao;
import sample.ConexaoBanco;
import sample.FormataPreco;
import sample.Main;
import sun.misc.FormattedFloatingDecimal;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TelaVisualizarAvaliacaoController implements Initializable{

    private ObservableList<Avaliacao> listaAvaliacao = FXCollections.observableArrayList();
    private Rating ratingMedia = new Rating(), ratingComida = new Rating(), ratingAtendimento = new Rating()
            , ratingAplicativo = new Rating();
    @FXML
    private Label labelMedia, labelAtendimento, labelComida, labelAplicativo;
    @FXML
    private ListView listaComentarios;
    @FXML
    private AnchorPane paneMedia, paneAvaliacoes;

    private void buscaAvaliacoes(){
        listaComentarios.getItems().clear();
        try {
            Statement stmt = ConexaoBanco.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from avaliacao;");

            while(rs.next()){
                listaAvaliacao.add(new Avaliacao(rs.getInt("codavaliacao"),rs.getInt("notacomida"),
                        rs.getInt("notaatendimento"),rs.getInt("notaaplicativo"),rs.getString("comentario")));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private float calculaMedia(){
        float media, mediaTotal = 0f, soma;

        for(int i = 0; i < listaAvaliacao.size(); i++){
            soma = 0;
            media = 0;
            soma += listaAvaliacao.get(i).getNota_aplicativo()+listaAvaliacao.get(i).getNota_atendimento()+listaAvaliacao.get(i).getNota_comida();
            media = soma / 3;
            mediaTotal += media;
        }

        return mediaTotal / listaAvaliacao.size();
    }

    private void criaGridPane(ObservableList lista){
        GridPane gridPane = new GridPane();

    }

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buscaAvaliacoes();
        ratingMedia.getStylesheets().add("sample/css/telaAvaliacao.css");
        ratingMedia.getStyleClass().add("rating");
        ratingMedia.setLayoutX(380);
        ratingMedia.setLayoutY(20);
        ratingMedia.setPartialRating(true);
        ratingMedia.setDisable(true);
        paneMedia.getChildren().add(ratingMedia);

        ratingAtendimento.getStylesheets().add("sample/css/telaAvaliacao.css");
        ratingAtendimento.getStyleClass().add("rating");
        ratingAtendimento.setLayoutX(350);
        ratingAtendimento.setLayoutY(12);
        ratingAtendimento.setPartialRating(true);
        ratingAtendimento.setDisable(true);

        ratingAplicativo.getStylesheets().add("sample/css/telaAvaliacao.css");
        ratingAplicativo.getStyleClass().add("rating");
        ratingAplicativo.setLayoutX(350);
        ratingAplicativo.setLayoutY(126);
        ratingAplicativo.setPartialRating(true);
        ratingAplicativo.setDisable(true);

        ratingComida.getStylesheets().add("sample/css/telaAvaliacao.css");
        ratingComida.getStyleClass().add("rating");
        ratingComida.setLayoutX(350);
        ratingComida.setLayoutY(240);
        ratingComida.setPartialRating(true);
        ratingComida.setDisable(true);

        paneAvaliacoes.getChildren().addAll(ratingAplicativo,ratingAtendimento,ratingComida);

        ratingMedia.setRating(calculaMedia());
        ratingAtendimento.setRating(3.4);
        System.out.println(calculaMedia());
        labelMedia.setText(String.format("%.1f",calculaMedia()));
    }
}

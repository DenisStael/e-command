package sample.TelaEditarPrato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class TelaEditarPratoController {
    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoPesquisar(ActionEvent actionEvent) {
    }

    public void acaoBuscar(ActionEvent actionEvent) {
    }

    /*private ObservableList<Prato> busca(){
        ObservableList<Prato> pratoPesquisa = (ObservableList<Prato>) FXCollections.observableArrayList();

        return pratoPesquisa; */

    }


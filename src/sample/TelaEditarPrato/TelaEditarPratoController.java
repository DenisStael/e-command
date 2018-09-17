package sample.TelaEditarPrato;

import sample.Main;
import java.io.IOException;

public class TelaEditarPratoController {
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoBuscar() {
    }

    /*private ObservableList<Prato> busca(){
        ObservableList<Prato> pratoPesquisa = (ObservableList<Prato>) FXCollections.observableArrayList();

        return pratoPesquisa;
    }*/
}


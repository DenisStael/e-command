package sample.TelaGarcom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Aviso;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaAvisosController implements Initializable {
    private static ObservableList<Aviso> solicitacoes = FXCollections.observableArrayList();
    @FXML
    public TableView<Aviso> tabelaAvisos;
    @FXML
    public TableColumn colunaNumMesa,colunaAviso;

    public static ObservableList<Aviso> getSolicitacoes() {
        return solicitacoes;
    }

    public static void setSolicitacoes(ObservableList<Aviso> solicitacoes) {
        TelaAvisosController.solicitacoes = solicitacoes;
    }

    public static void removeLista(int numeroMesa){
        for(int i = 0; i < solicitacoes.size(); i++){
            if(solicitacoes.get(i).getNumeroMesa() == numeroMesa){
                solicitacoes.remove(i);
            }
        }
    }

    public static void insereLista(int numeroMesa, String informacao){
            solicitacoes.add(new Aviso(numeroMesa,informacao));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNumMesa.setCellValueFactory(new PropertyValueFactory<>("numeroMesa"));
        colunaAviso.setCellValueFactory(new PropertyValueFactory<>("informacao"));
        tabelaAvisos.setItems(solicitacoes);

    }
}

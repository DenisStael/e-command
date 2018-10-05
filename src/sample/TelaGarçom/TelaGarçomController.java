package sample.TelaGarçom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.Pedido;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaGarçomController implements Initializable {
    private Pedido pedido = new Pedido();
    private String sql = "select codpedido,mesa_idmesa,observacao from pedido where garcom_usuario_codusuario is null;";
    @FXML
    private TableView<Pedido> tabelaGarcom;
    @FXML
    private TableColumn colunaCodPedido,colunaNumMesa,colunaObservacao;

    public void acaoVoltar(ActionEvent actionEvent) throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }
    public void acaoInformacaoGarcom() throws IOException {
        if(tabelaGarcom.getSelectionModel().getSelectedItem() != null){
            TelaInformacaoGarcomController.codPedido = tabelaGarcom.getSelectionModel().getSelectedItem().getCodpedido();
            Stage stage = new Stage();
            stage.setTitle("Informações do Pedido");
            Parent root = FXMLLoader.load(getClass().getResource("telaInformacaoGarcom.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pedido.mostraTabelaGarcom(tabelaGarcom,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);
    }
}

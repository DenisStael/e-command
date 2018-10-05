package sample.TelaCozinheiro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import sample.Pedido;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class TelaCozinheiroController implements Initializable {
    private Pedido pedido = new Pedido();
    private String sql = "select codpedido,mesa_idmesa,observacao from pedido where cozinheiro_usuario_codusuario is null;";
    @FXML
    private TableView tabelaCozinheiro;
    @FXML
    private TableColumn colunaCodPedido,colunaNumMesa,colunaObservacao;
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public void acaoInformacaoCozinheiro(MouseEvent mouseEvent) throws IOException {
        if(tabelaCozinheiro.getSelectionModel().getSelectedItem() != null){
            Stage stage = new Stage();
            stage.setTitle("Informações do Pedido");
            Parent root = FXMLLoader.load(getClass().getResource("telaInformacaoCozinheiro.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pedido.mostraTabelaGarcom(tabelaCozinheiro,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);
    }
}

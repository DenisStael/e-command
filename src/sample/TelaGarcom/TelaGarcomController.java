package sample.TelaGarcom;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Main;
import sample.Pedido;
import sample.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaGarcomController implements Initializable {
    private Stage stage = new Stage();
    private static Usuario usuario;
    private Pedido pedido = new Pedido();
    private String sql = "select codpedido,mesa_idmesa,observacao from pedido where garcom_usuario_codusuario is null order by codpedido;";
    @FXML
    private TableView<Pedido> tabelaGarcom;
    @FXML
    private TableColumn colunaCodPedido,colunaNumMesa,colunaObservacao;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        TelaGarcomController.usuario = usuario;
    }

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }
    public void acaoInformacaoGarcom() throws IOException {
        if(tabelaGarcom.getSelectionModel().getSelectedItem() != null){
            TelaInformacaoGarcomController.codPedido = tabelaGarcom.getSelectionModel().getSelectedItem().getCodpedido();
            stage.setTitle("Informações do Pedido");
            Parent root = FXMLLoader.load(getClass().getResource("telaInformacaoGarcom.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pedido.mostraTabela(tabelaGarcom,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();//Fecha a aplicação
                pedido.mostraTabela(tabelaGarcom,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);
            }
        });
    }
}

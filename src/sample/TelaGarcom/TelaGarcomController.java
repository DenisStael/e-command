package sample.TelaGarcom;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.*;
import sample.TelaAtendimento.TelaAtendimentoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaGarcomController extends Logout implements Initializable {
    private Stage stage = new Stage();
    private static Usuario usuario;
    private TabelaLista pedido = new TabelaLista();
    private String sql = "select pe.codpedido,c.id_mesa,pe.observacao from pedido pe,comanda c where pe.garcom_usuario_codusuario is null " +
            " and c.codcomanda = pe.codcomanda order by pe.codpedido;";
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

    @FXML
    protected void acaoAviso() throws IOException {
        Image icone = new Image(getClass().getResourceAsStream("../img/icone.png"));
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../TelaGarcom/telaAvisos.fxml"));
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Main.stage);
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();
        stage.getIcons().add(icone);
        stage.setTitle("Avisos");
        stage.setScene(scene);
        stage.show();
    }

    public void acaoVoltar() throws IOException {
        acaoSair();
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

    public void acaoAtendimentos() throws IOException {
        TelaAtendimentoController.setUsuario(usuario);
        Main.trocaTela("TelaAtendimento/telaAtendimento.fxml");
    }

    public void acaoAtualizar() {
        pedido.mostraTabelaPedidos(tabelaGarcom,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pedido.mostraTabelaPedidos(tabelaGarcom,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();//Fecha a aplicação
                acaoAtualizar();
            }
        });
    }

}

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
import sample.TelaGarcom.TelaGarcomController;
import sample.TelaGarcom.TelaInformacaoGarcomController;
import sample.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCozinheiroController implements Initializable {

    private static Usuario usuario;
    private Pedido pedido = new Pedido();
    private String sql = "select distinct p.codpedido,p.mesa_idmesa,p.observacao from pedido p, prato pr, pedidoprato pp " +
            "where p.cozinheiro_usuario_codusuario is null and pp.codpedido = p.codpedido and pp.codprato = pr.codprato " +
            "and pr.tipo = 'Comida' order by p.codpedido;";
    @FXML
    private TableView<Pedido> tabelaCozinheiro;
    @FXML
    private TableColumn colunaCodPedido,colunaNumMesa,colunaObservacao;
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaLogin/telaLogin.fxml");
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        TelaCozinheiroController.usuario = usuario;
    }

    public void acaoInformacaoCozinheiro() throws IOException {
        if(tabelaCozinheiro.getSelectionModel().getSelectedItem() != null){
            TelaInformacaoCozinheiroController.codPedido = tabelaCozinheiro.getSelectionModel().getSelectedItem().getCodpedido();
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
        pedido.mostraTabela(tabelaCozinheiro,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);
    }
}

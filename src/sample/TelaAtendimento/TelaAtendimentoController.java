package sample.TelaAtendimento;

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
import sample.TabelaLista;
import sample.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaAtendimentoController implements Initializable {

    private Stage stage = new Stage();
    private static Usuario usuario;
    private TabelaLista tabelaLista = new TabelaLista();
    private String sql;
    @FXML
    private TableView<Pedido> tabelaPedido;
    @FXML
    private TableColumn colunaCodPedido,colunaNumMesa,colunaObservacao;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        TelaAtendimentoController.usuario = usuario;
    }

    public void acaoInformacao() throws IOException {
        stage.setTitle("Meus Atendimentos");
        Parent root = FXMLLoader.load(getClass().getResource("telaInformacao.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void acaoVoltar() throws IOException {
        if(usuario.getTipo().equals("Garçom"))
            Main.trocaTela("TelaGarcom/telaGarcom.fxml");
        else if(usuario.getTipo().equals("Cozinheiro"))
            Main.trocaTela("TelaCozinheiro/telaCozinheiro.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(usuario.getTipo().equals("Garçom"))
            sql = "select codpedido, mesa_idmesa, observacao from pedido where garcom_usuario_codusuario = "+usuario.getCodusuario()+";";
        else if(usuario.getTipo().equals("Cozinheiro"))
            sql = "select codpedido, mesa_idmesa, observacao from pedido where cozinheiro_usuario_codusuario = "+usuario.getCodusuario()+";";

        tabelaLista.mostraTabelaPedidos(tabelaPedido,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();//Fecha a aplicação
                tabelaLista.mostraTabelaPedidos(tabelaPedido,colunaCodPedido,colunaNumMesa,colunaObservacao,sql);
            }
        });
    }
}

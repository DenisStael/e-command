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
import sample.*;

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
    private TableColumn<Pedido, String> colunaNumMesa,colunaObservacao;
    @FXML
    private TableColumn<Pedido, Integer> colunaCodPedido ,colunaStatus;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        TelaAtendimentoController.usuario = usuario;
    }

    public void acaoInformacao() throws IOException {
        if(tabelaPedido.getSelectionModel().getSelectedItem() != null){
            TelaInformacaoController.codPedido = tabelaPedido.getSelectionModel().getSelectedItem().getCodpedido();
            stage.setTitle("Meus Atendimentos");
            Parent root = FXMLLoader.load(getClass().getResource("telaInformacao.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void acaoVoltar() throws IOException {
        if(usuario.getTipo().equals("Garçom"))
            Main.trocaTela("TelaGarcom/telaGarcom.fxml");
        else if(usuario.getTipo().equals("Cozinheiro"))
            Main.trocaTela("TelaCozinheiro/telaCozinheiro.fxml");
    }

    private void montaTabela(){
        tabelaLista.mostraPedidosAtendimento(tabelaPedido,colunaCodPedido,colunaNumMesa,colunaObservacao,colunaStatus,sql);
        Integer status;
        for(int i = 0; i < tabelaPedido.getItems().size(); i++){
            status = tabelaPedido.getItems().get(i).getStatus();
            tabelaLista.criaCelula(colunaStatus,status);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(usuario.getTipo().equals("Garçom"))
            sql = "select distinct pe.codpedido, pe.mesa_idmesa, pe.observacao, pp.codcozinheiro from pedido pe, pedidoprato pp " +
                    "where pe.garcom_usuario_codusuario = "+usuario.getCodusuario()+" and pp.codpedido = pe.codpedido and " +
                    "(pp.codgarcom is null or pp.codcozinheiro is null);";
        else if(usuario.getTipo().equals("Cozinheiro"))
            sql = "select distinct pe.codpedido, pe.mesa_idmesa, pe.observacao, pp.codcozinheiro from pedido pe, pedidoprato pp " +
                    "where pe.cozinheiro_usuario_codusuario = "+usuario.getCodusuario()+" and pp.codpedido = pe.codpedido and " +
                    "pp.codcozinheiro is null;";

        montaTabela();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();//Fecha a aplicação
                montaTabela();
            }
        });
    }
}

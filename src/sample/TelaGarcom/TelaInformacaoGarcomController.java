package sample.TelaGarcom;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.ConexaoBanco;
import sample.Prato;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TelaInformacaoGarcomController implements Initializable {
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private Prato tabelaPrato = new Prato();
    public static int codPedido;
    @FXML
    private TableView<Prato> tabelaPratos;
    @FXML
    private TableColumn colunaPrato, colunaDescPrato, colunaCodPrato, colunaPrecoPrato;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "select p.nome, p.codprato, p.descricao, p.preco, pe.codpedido "+
                "from prato p, pedido pe, pedidoprato pp "+
                "where p.codprato = pp.codprato and pe.codpedido = "+codPedido+" and pe.codpedido = pp.codpedido;";
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPrecoPrato,sql);
    }
    public void acaoAtenderPedido() throws SQLException {
        /*Aqui tem que inserir o codigo do garçom no pedido para demonstrar que o pedido foi atendido
          e para o pedido sair da tabela de pedidos para serem atendidos*/

        /*PreparedStatement ps = conexaoBanco.connection.prepareStatement("update pedido set garcom_usuario_codusuario = ? where codpedido = ?;");
        ps.setInt(1, );
        ps.setInt(2,codPedido);
        ps.executeUpdate();*/
    }

}

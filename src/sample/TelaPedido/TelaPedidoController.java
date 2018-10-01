package sample.TelaPedido;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConexaoBanco;
import sample.Main;
import sample.Pedido;
import sample.Prato;
import sample.TelaVisualizarCardapio.TelaVisualizarCardapioController;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TelaPedidoController implements Initializable {

    public static int numeroMesa;
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private static Pedido pedido = new Pedido();
    @FXML
    private TextArea txtObservacao;
    @FXML
    private TableView<Prato> tabelaPedido;
    @FXML
    private TableColumn colunaNome, colunaPreco;
    @FXML
    private Label labelPrecoTotal, labelNumeroMesa;
    @FXML
    private Button btConfirmarPedido;

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        TelaPedidoController.pedido = pedido;
    }

    public void acaoVoltar() throws IOException {
        TelaVisualizarCardapioController.setPedido(pedido);
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    public void acaoConfirmar(){
        try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement
                    ("insert into pedido(mesa_idmesa,observacao,statuspedido)values(?,?,'Aberto');");
            ps.setInt(1,numeroMesa);

            if(txtObservacao.getText().isEmpty())
                ps.setString(2,null);
            else
                ps.setString(2,txtObservacao.getText());

            ps.executeUpdate();

            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select max(codpedido) as qtd from pedido;");

            if(rs.next()){
                PreparedStatement ps2 = conexaoBanco.connection.prepareStatement
                        ("insert into pedidoprato(codprato,codpedido)values(?,?);");
                ps2.setInt(2,rs.getInt("qtd"));

                PreparedStatement ps3 = conexaoBanco.connection.prepareStatement
                        ("update prato set quantidade = quantidade + 1 where codprato = ?;");

                for(int i = 0; i < pedido.getListaPedido().size(); i++){
                    ps2.setInt(1, Integer.parseInt(pedido.getListaPedido().get(i).getCodprato()));
                    ps2.executeUpdate();
                    ps3.setInt(1, Integer.parseInt(pedido.getListaPedido().get(i).getCodprato()));
                    ps3.executeUpdate();
                }
            }
            rs.close();
            tabelaPedido.getItems().clear();
            Main.trocaTela("TelaComanda/telaComanda.fxml");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao realizar pedido!\n"+e);
        }
    }

    private float calculaPreco(){
        Float precoTotal = 0f;
        for(int i = 0; i < pedido.getListaPedido().size(); i++){
            precoTotal += Float.parseFloat(pedido.getListaPedido().get(i).getPreco());
        }
        return precoTotal;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaPedido.setItems(pedido.getListaPedido());
        labelNumeroMesa.setText(Integer.toString(numeroMesa));
        labelPrecoTotal.setText(Float.toString(calculaPreco()));
        if(!tabelaPedido.getItems().isEmpty())
            btConfirmarPedido.setDisable(false);
        else
            btConfirmarPedido.setDisable(true);
    }
}

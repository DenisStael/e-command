package sample.TelaComanda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.ConexaoBanco;
import sample.Main;
import sample.TelaGarcom.TelaAvisosController;
import sample.TelaPedido.TelaPedidoController;
import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaPagamentoController {

    public static String precoTotal;
    @FXML
    private Label labelPagamento,labelFinalizado,labelInfo,labelValorTotal,labelPrecoTotal;
    @FXML
    private Button botaoPagarCartao,botaoPagarDinheiro,botaoSair;

    public void acaoPagarCartao() {
        TelaAvisosController.insereLista(TelaPedidoController.numeroMesa,"Pagamento em cartão");
        try {
            economizaCodigo();
            fecharPedido();
            labelPagamento.setText("Aguarde enquanto o garçom traz a máquina de cartão!");
            labelValorTotal.setText("Valor total: R$");
            labelPrecoTotal.setText(precoTotal);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    private void fecharPedido(){
        try {
            PreparedStatement ps = ConexaoBanco.getConnection().prepareStatement
                    ("UPDATE comanda SET statuscomanda = 'Fechado' WHERE id_mesa = ? AND statuscomanda = 'Aberto';");
            ps.setInt(1, TelaPedidoController.numeroMesa);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acaoPagarDinheiro() {
        try {
            economizaCodigo();
            labelPagamento.setText("Por favor dirija-se ao balcão para realizar o pagamento!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void economizaCodigo(){
        try {
            PreparedStatement ps = ConexaoBanco.getConnection().prepareStatement
                    ("UPDATE pedido SET statuspedido = 'Fechado' WHERE codcomanda = ? AND statuspedido = 'Aberto';");
            ps.setInt(1,TelaPedidoController.numeroComanda);
            ps.executeUpdate();
            TelaPedidoController.numeroComanda = null;
            TelaComandaController.numeroComanda = 0;
            botaoPagarCartao.setDisable(true);
            botaoPagarCartao.setVisible(false);
            botaoPagarDinheiro.setDisable(true);
            botaoPagarDinheiro.setVisible(false);
            botaoSair.setVisible(true);
            botaoSair.setDisable(false);
            labelInfo.setVisible(false);
            labelFinalizado.setText("FINALIZADO");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void acaoSair() throws IOException {
        TelaComandaController.stage.close();
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
        TelaAvisosController.removeLista(TelaPedidoController.numeroMesa);
    }
}

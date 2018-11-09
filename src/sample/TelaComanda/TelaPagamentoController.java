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
public class TelaPagamentoController {
    @FXML
    private Label labelPagamento,labelFinalizado,labelInfo;
    @FXML
    private Button botaoPagarCartao,botaoPagarDinheiro,botaoSair;

    public void acaoPagarCartao() {
        TelaAvisosController.insereLista(TelaPedidoController.numeroMesa,"Pagamento em cartão");
        try {
            economizaCodigo();
            labelPagamento.setText("Aguarte enquanto o garçom traz a maquina de cartão!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
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
            PreparedStatement ps = ConexaoBanco.getConnection().prepareStatement("UPDATE comanda SET statuscomanda = ? WHERE id_mesa = ?");
            ps.setString(1,"Fechado");
            ps.setInt(2,TelaPedidoController.numeroMesa);
            ps.executeUpdate();
            TelaPedidoController.numeroComanda = null;
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
    }
}

package sample.TelaAtendimento;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.ConexaoBanco;
import sample.Prato;
import sample.TabelaLista;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class TelaInformacaoController implements Initializable {

    private String sql = "";
    public static int codPedido;
    private int codPrato;
    private TabelaLista tabelaLista = new TabelaLista();
    @FXML
    private Button botaoSituacao;
    @FXML
    private TableView<Prato> tabelaPratos;
    @FXML
    private TableColumn colunaPrato, colunaDescPrato, colunaCodPrato;
    @FXML
    private TableColumn<Prato, Integer> colunaStatus;

    public void acaoFinalizar(){
        String sql_2 = "";
        if(TelaAtendimentoController.getUsuario().getTipo().equals("Garçom")){
            sql_2 = "update pedidoprato set codgarcom = ? where codprato = ? and codpedido = ?;";
        } else if(TelaAtendimentoController.getUsuario().getTipo().equals("Cozinheiro")){
            sql_2 = "update pedidoprato set codcozinheiro = ? where codprato = ? and codpedido = ?;";
        }
        try {
            PreparedStatement ps = ConexaoBanco.getConnection().prepareStatement(sql_2);
            ps.setInt(1, TelaAtendimentoController.getUsuario().getCodusuario());
            ps.setInt(2, codPrato);
            ps.setInt(3, codPedido);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void acaoTabela(){
        if(tabelaPratos.getSelectionModel().getSelectedItem() != null){
            codPrato = Integer.parseInt(tabelaPratos.getSelectionModel().getSelectedItem().getCodprato());
        }
    }

    private void montaTabela(){
        tabelaLista.mostraPratosAtendimento(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaStatus,sql);
        Integer codcozinheiro, codgarcom;
        for(int i = 0; i < tabelaPratos.getItems().size(); i++){
            codcozinheiro = tabelaPratos.getItems().get(i).getCodcozinheiro();
            codgarcom = tabelaPratos.getItems().get(i).getCodgarcom();
            tabelaLista.criaCelulaPrato(colunaStatus,codcozinheiro,codgarcom);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(TelaAtendimentoController.getUsuario().getTipo().equals("Garçom")){
            botaoSituacao.setText("Prato entregue");
            sql = "select p.nome, p.descricao, p.codprato ,p.preco, p.imagem, pp.codcozinheiro, pp.codgarcom from prato p, pedido pe, pedidoprato pp where " +
                    "p.codprato = pp.codprato and pe.codpedido = pp.codpedido and pe.codpedido = "+codPedido+";";
        } else if(TelaAtendimentoController.getUsuario().getTipo().equals("Cozinheiro")){
            botaoSituacao.setText("Prato Finalizado");
            sql = "select p.nome, p.descricao, p.codprato ,p.preco, p.imagem, pp.codcozinheiro, pp.codgarcom from prato p, pedido pe, pedidoprato pp where " +
                    "p.codprato = pp.codprato and p.tipo = 'Comida' and pe.codpedido = pp.codpedido and pe.codpedido = "+codPedido+";";
        }

        montaTabela();
    }
}

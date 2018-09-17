package sample.TelaMontarPrato;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import sample.ConexaoBanco;
import sample.Main;
import javafx.scene.control.TextField;
import sample.Produto;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TelaMontarPratoController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    @FXML
    TextField txtNomePrato, txtPreco, txtAdicionaProduto;
    @FXML
    TextArea txtDescricao;
    @FXML
    TableColumn colunaProdutos, colunaAddProd, colunaAddCod, colunaAddDescricao, colunaCodigoProdutos;
    @FXML
    TableView<Produto> tabelaProdutos;

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoCancelar(){
        txtNomePrato.clear(); txtPreco.clear();
        txtDescricao.clear(); txtAdicionaProduto.clear();
    }

    public void acaoSalvar(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select nome from produto where codproduto = 1;");
            while (rs.next()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}

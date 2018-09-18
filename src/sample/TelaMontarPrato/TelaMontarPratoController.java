package sample.TelaMontarPrato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import sample.ConexaoBanco;
import sample.Main;
import sample.Produto;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class TelaMontarPratoController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();
    @FXML
    TextField txtNomePrato, txtPreco, txtAdicionaProduto;
    @FXML
    TextArea txtDescricao;
    @FXML
    TableColumn<Produto, String> colunaProd, colunaAddProd, colunaDescricao;
    @FXML
    TableColumn<Produto, Integer> colunaAddCod, colunaCod;
    @FXML
    TableView<Produto> tabelaProdutos, tabelaAddProdutos;
    ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    ObservableList<Produto> listaAddProdutos = FXCollections.observableArrayList();

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    public void acaoCancelar(){
        txtNomePrato.clear(); txtPreco.clear();
        txtDescricao.clear(); txtAdicionaProduto.clear();
    }

    public void acaoSalvar(){
        /*try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select nome,codproduto,descricao from produto;");
            while (rs.next()){
                listaProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto"), rs.getString("descricao")));
            }
            rs.close();
            colunaProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tabelaProdutos.setItems(listaProdutos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar produtos!\n"+e);
        }
    }
}

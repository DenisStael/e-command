package sample.TelaMontarPrato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.control.Label;
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
    @FXML
    Label labelProd;
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
        try {
            PreparedStatement ps = conexaoBanco.connection.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acaoAdicionaProduto(){
        if(!txtAdicionaProduto.getText().isEmpty()){
            try {
                int cod = Integer.parseInt(txtAdicionaProduto.getText());
                PreparedStatement ps = conexaoBanco.connection.prepareStatement("select nome,codproduto from produto where codproduto = ?;");
                ps.setInt(1, cod);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    listaAddProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto")));
                } else labelProd.setText("Produto não encontrado!");
                colunaAddProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
                colunaAddCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
                tabelaAddProdutos.setItems(listaAddProdutos);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Digite apenas números!\n"+e);
            }
        } else System.out.println("Nada");
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

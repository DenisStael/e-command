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

    public void acaoLimpar(){
        txtNomePrato.clear(); txtPreco.clear();
        txtDescricao.clear(); txtAdicionaProduto.clear();
        tabelaAddProdutos.getItems().clear(); labelProd.setText(null);
    }

    public void acaoSalvar(){
        if(!txtNomePrato.getText().isEmpty() && !txtPreco.getText().isEmpty() && !txtDescricao.getText().isEmpty() && !listaAddProdutos.isEmpty()){
            try {
                PreparedStatement ps = conexaoBanco.connection.prepareStatement("insert into prato(nome,preco,descricao)values(?,?,?);");
                ps.setString(1, txtNomePrato.getText());
                ps.setFloat(2, Float.parseFloat(txtPreco.getText()));
                ps.setString(3, txtDescricao.getText());
                ps.executeUpdate();
                Statement stmt = conexaoBanco.connection.createStatement();
                ResultSet rs = stmt.executeQuery("select max(codprato) codMax from prato;");
                int codMax = 0;
                if(rs.next()) {
                    codMax = rs.getInt("codMax");
                }
                rs.close();
                PreparedStatement ps2;
                for(int i = 0; i < listaAddProdutos.size(); i++){
                    ps2 = conexaoBanco.connection.prepareStatement("insert into pratoprodutos(codprato,codproduto) values(?,?);");
                    ps2.setInt(1, codMax);
                    ps2.setInt(2, listaAddProdutos.get(i).getCodproduto());
                    ps2.executeUpdate();
                    ps2.close();
                }
                acaoLimpar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Digite apenas números!\n"+e);
            }
        }else JOptionPane.showMessageDialog(null, "Preencha todos os campos para adicionar!");
    }

    public void acaoAdicionaProduto(){
        labelProd.setText(null);
        boolean prodIsDif = true;
        if(!txtAdicionaProduto.getText().isEmpty()){
            try {
                int cod = Integer.parseInt(txtAdicionaProduto.getText());
                for(int i = 0; i < listaAddProdutos.size(); i++){
                    if(listaAddProdutos.get(i).getCodproduto() == cod){
                        labelProd.setText("Produto já adicionado!");
                        prodIsDif = false;
                    }
                }
                if(prodIsDif){
                    PreparedStatement ps = conexaoBanco.connection.prepareStatement("select nome,codproduto from produto where codproduto = ?;");
                    ps.setInt(1, cod);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        listaAddProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto")));
                    } else labelProd.setText("Produto não encontrado!");
                    colunaAddProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    colunaAddCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
                    tabelaAddProdutos.setItems(listaAddProdutos);
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Digite apenas números!\n"+e);
            }
        }
        txtAdicionaProduto.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select nome,codproduto,descricao from produto order by codproduto;");
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
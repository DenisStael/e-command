package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.sql.*;

public class TabelaProduto {

    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    private ConexaoBanco conexaoBanco = new ConexaoBanco();

    public void mostraTabela(TableView tabelaProdutos, TableColumn colunaProd, TableColumn colunaDescricao, TableColumn colunaCod, TableColumn colunaQuantidade, String sql){
        try {
            this.listaProdutos.clear();
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                this.listaProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto"),
                        rs.getString("descricao"), rs.getInt("quantidade")));
            }
            rs.close();
            colunaProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tabelaProdutos.setItems(this.listaProdutos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar produtos!\n"+e);
        }
    }
}

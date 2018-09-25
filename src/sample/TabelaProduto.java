package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.sql.*;

public class TabelaProduto {

    //Lista de objetos do tipo produto
    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

    private ConexaoBanco conexaoBanco = new ConexaoBanco(); //objeto para conexão com o banco

    /*método que mostra a tabela com todos os produtos em estoque e
    recebe como parâmetros a tabela que será apresentada, suas colunas e a string sql*/
    public void mostraTabela(TableView tabelaProdutos, TableColumn colunaProd, TableColumn colunaDescricao, TableColumn colunaCod, TableColumn colunaQuantidade, TableColumn colunaMedida, String sql){
        try {
            this.listaProdutos.clear();//limpa a lista
            Statement stmt = conexaoBanco.connection.createStatement();//cria declaração sql
            ResultSet rs = stmt.executeQuery(sql); //executa a declaração e armazena o resultado

            //enquanto há resultados na consulta, registra os produtos na lista
            while (rs.next()){
                this.listaProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto"),
                        rs.getString("descricao"), rs.getInt("quantidade"), rs.getString("medida")));
            }
            rs.close(); //fecha o resultset

            //atribui às colunas da tabela os valores
            colunaProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            colunaMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));

            //insere os itens na tabela
            tabelaProdutos.setItems(this.listaProdutos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar produtos!\n"+e);
        }
    }
}

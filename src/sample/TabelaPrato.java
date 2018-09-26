package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TabelaPrato {
    //Lista de objetos do tipo prato
    private ObservableList<Prato> listaPratos = FXCollections.observableArrayList();

    private ConexaoBanco conexaoBanco = new ConexaoBanco(); //objeto para conexão com o banco

    /*método que mostra a tabela com todos os pratos em estoque e
    recebe como parâmetros a tabela que será apresentada, suas colunas e a string sql*/
    public void mostraTabela(TableView tabelaPratos, TableColumn colunaPrato, TableColumn colunaDescricao, TableColumn colunaCod, TableColumn colunaPreco, String sql) {
        try {
            this.listaPratos.clear();//limpa a lista
            Statement stmt = conexaoBanco.connection.createStatement();//cria declaração sql
            ResultSet rs = stmt.executeQuery(sql); //executa a declaração e armazena o resultado

            //enquanto há resultados na consulta, registra os pratos na lista
            while (rs.next()) {
                this.listaPratos.add(new Prato(rs.getString("nome"), rs.getInt("codprato"),
                        rs.getString("descricao"), rs.getFloat("preco")));
            }
            rs.close(); //fecha o resultset

            //atribui às colunas da tabela os valores
            colunaPrato.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codprato"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

            //insere os itens na tabela
            tabelaPratos.setItems(this.listaPratos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
    }

    public void mostraTabelaPrato(TableView tabelaPratoCardapio, TableColumn colunaNomePrato, TableColumn colunaDescricaoPrato, TableColumn colunaPrecoPrato, String sql) {
        ObservableList<Prato> listaPratos = FXCollections.observableArrayList();
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
            listaPratos.add(new Prato(rs.getString("nome"),
                    rs.getString("descricao"),rs.getFloat("preco")));
            }
            rs.close();

            colunaNomePrato.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaDescricaoPrato.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaPrecoPrato.setCellValueFactory(new PropertyValueFactory<>("preco"));

            tabelaPratoCardapio.setItems(listaPratos);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
    }
}

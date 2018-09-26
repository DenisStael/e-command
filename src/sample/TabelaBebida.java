package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

public class TabelaBebida {
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private ObservableList<Bebida> listaBebida = FXCollections.observableArrayList();

    public void mostraTabelaBebida(TableView tabelaBebida, TableColumn colunaNome, TableColumn colunaDescricao,
                                   TableColumn colunaPreco, String sql){
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                listaBebida.add(new Bebida(rs.getFloat("preco"),
                        rs.getString("nome"),rs.getString("descricao")));
            }
            rs.close();
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));


            tabelaBebida.setItems(this.listaBebida);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
    }
}

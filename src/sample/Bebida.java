package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bebida extends Produto {

    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private ObservableList<Bebida> listaBebida = FXCollections.observableArrayList();

    private SimpleFloatProperty preco;
    private SimpleIntegerProperty codbebida;

    public Bebida(float preco, String nome, int codbebida, String descricao){
        super(nome,descricao);
        this.preco = new SimpleFloatProperty(preco);
        this.codbebida = new SimpleIntegerProperty(codbebida);
    }

    public Bebida(){
    }

    public float getPreco() {
        return preco.get();
    }

    public SimpleFloatProperty precoProperty() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco.set(preco);
    }

    public int getCodbebida() {
        return codbebida.get();
    }

    public SimpleIntegerProperty codbebidaProperty() {
        return codbebida;
    }

    public void setCodbebida(int codbebida) {
        this.codbebida.set(codbebida);
    }

    public void mostraTabelaBebida(TableView tabelaBebida, TableColumn colunaNome, TableColumn colunaPreco, String sql){
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                listaBebida.add(new Bebida(rs.getFloat("preco"), rs.getString("nome"),
                        rs.getInt("codbebida"),rs.getString("descricao")));
            }
            rs.close();
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

            tabelaBebida.setItems(listaBebida);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
    }

}

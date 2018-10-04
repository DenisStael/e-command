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
import java.sql.Statement;

public class Pedido {
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private static ObservableList<Prato> listaPedido = FXCollections.observableArrayList();
    private SimpleIntegerProperty codpedido;
    private SimpleIntegerProperty idmesa;
    private SimpleFloatProperty preco;

    public Pedido(int codpedido, int idmesa, float preco) {
        this.codpedido = new SimpleIntegerProperty(codpedido);
        this.idmesa = new SimpleIntegerProperty(idmesa);
        this.preco = new SimpleFloatProperty(preco);
    }

    public Pedido(){}

    public ObservableList<Prato> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(ObservableList<Prato> listaPedido) {
        this.listaPedido = listaPedido;
    }

    public int getCodpedido() {
        return codpedido.get();
    }

    public SimpleIntegerProperty codpedidoProperty() {
        return codpedido;
    }

    public void setCodpedido(int codpedido) {
        this.codpedido.set(codpedido);
    }

    public int getIdmesa() {
        return idmesa.get();
    }

    public SimpleIntegerProperty idmesaProperty() {
        return idmesa;
    }

    public void setIdmesa(int idmesa) {
        this.idmesa.set(idmesa);
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

    public void mostraTabelaPedido(TableView tabelaPedido, TableColumn colunaCodPedido, TableColumn colunaIdMesa, TableColumn colunaPreco, String sql) {
        ObservableList<Pedido> listaPedidos = FXCollections.observableArrayList();
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                listaPedidos.add(new Pedido(rs.getInt("codpedido"),rs.getInt("mesa_idmesa"),rs.getFloat("precototal")));
            }
            rs.close();

            colunaCodPedido.setCellValueFactory(new PropertyValueFactory<>("codpedido"));
            colunaIdMesa.setCellValueFactory(new PropertyValueFactory<>("idmesa"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

            tabelaPedido.setItems(listaPedidos);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
    }
}

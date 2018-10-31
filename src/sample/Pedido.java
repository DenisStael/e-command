package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private SimpleStringProperty observacao;

    public Pedido(int codpedido, int idmesa, float preco) {
        this.codpedido = new SimpleIntegerProperty(codpedido);
        this.idmesa = new SimpleIntegerProperty(idmesa);
        this.preco = new SimpleFloatProperty(preco);
    }

    public Pedido(){}

    public Pedido(int codpedido, int idmesa, String observacao) {
        this.codpedido = new SimpleIntegerProperty(codpedido);
        this.idmesa = new SimpleIntegerProperty(idmesa);
        this.observacao = new SimpleStringProperty(observacao);

    }

    public ObservableList<Prato> getListaPedido() {
        return listaPedido;
    }

    public String getObservacao() {
        return observacao.get();
    }

    public SimpleStringProperty observacaoProperty() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao.set(observacao);
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

}

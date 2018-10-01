package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido {
    private static ObservableList<Prato> listaPedido = FXCollections.observableArrayList();

    public ObservableList<Prato> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(ObservableList<Prato> listaPedido) {
        this.listaPedido = listaPedido;
    }
}

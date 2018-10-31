package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TabelaLista {

    private ConexaoBanco conexaoBanco = new ConexaoBanco(); //objeto para conexão com o banco

    /*método que mostra a tabela com todos os pratos em estoque e
    recebe como parâmetros a tabela que será apresentada, suas colunas e a string sql*/
    public void mostraTabelaPratos(TableView tabelaPratos, TableColumn colunaPrato, TableColumn colunaDescricao, TableColumn colunaCod, TableColumn colunaPreco, String sql) {
        ObservableList<Prato> listaPratos = FXCollections.observableArrayList();
        try {
            listaPratos.clear();//limpa a lista
            Statement stmt = conexaoBanco.connection.createStatement();//cria declaração sql
            ResultSet rs = stmt.executeQuery(sql); //executa a declaração e armazena o resultado

            //enquanto há resultados na consulta, registra os pratos na lista
            while (rs.next()) {
                listaPratos.add(new Prato(rs.getString("nome"), rs.getInt("codprato"),
                        rs.getString("descricao"), rs.getFloat("preco")));
            }
            rs.close(); //fecha o resultset

            //atribui às colunas da tabela os valores
            colunaPrato.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codprato"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

            //insere os itens na tabela
            tabelaPratos.setItems(listaPratos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
    }

    private GridPane criaGridPane(String nome, String preco, String descricao/*, Image image*/){
        GridPane gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(100));
        gridPane.getColumnConstraints().add(new ColumnConstraints(320));
        gridPane.getColumnConstraints().add(new ColumnConstraints(90));
        gridPane.getStylesheets().add(String.valueOf(getClass().getResource("css/TableStyle.css")));
        gridPane.getStyleClass().add("gridPane");
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("img/chef.png")));
        Label lblNome = new Label(nome);
        lblNome.getStyleClass().add("nome");
        Label lblPreco = new Label(preco);
        lblPreco.getStyleClass().add("preco");
        Label lblDescricao = new Label(descricao);
        imageView.setFitWidth(95);
        imageView.setFitHeight(90);
        lblDescricao.setPrefWidth(380);
        lblDescricao.setWrapText(true);
        lblDescricao.getStyleClass().add("descricao");
        gridPane.setMaxWidth(380);
        gridPane.setPadding(new Insets(2,2,2,2));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        GridPane.setHalignment(lblNome, HPos.LEFT);
        gridPane.add(lblNome,1,0);
        GridPane.setHalignment(lblPreco,HPos.RIGHT);
        gridPane.add(lblPreco,2,0);
        gridPane.add(imageView,0,0,1,3);
        gridPane.add(lblDescricao,1,2,2,1);
        return gridPane;
    }

    public ObservableList<GridPane> listaPrato(String sql) {
        ObservableList<GridPane> listaPratos = FXCollections.observableArrayList();
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                listaPratos.add(criaGridPane(rs.getString("nome"),Float.toString(rs.getFloat("preco")),
                        rs.getString("descricao")));
            }
            rs.close();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n" + e);
        }
        return listaPratos;
    }


    //--------------------TABELAS E LISTAS DE PEDIDOS------------------//

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
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pedidos!\n" + e);
        }
    }
    public void mostraTabelaPedidos(TableView tabelaGarcom, TableColumn colunaCodPedido, TableColumn colunaIdMesa,TableColumn colunaObservacao,String sql) {
        ObservableList<Pedido> listaPedidos = FXCollections.observableArrayList();
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                listaPedidos.add(new Pedido(rs.getInt("codpedido"),rs.getInt("mesa_idmesa"),rs.getString("observacao")));
            }
            rs.close();

            colunaCodPedido.setCellValueFactory(new PropertyValueFactory<>("codpedido"));
            colunaIdMesa.setCellValueFactory(new PropertyValueFactory<>("idmesa"));
            colunaObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

            tabelaGarcom.setItems(listaPedidos);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pedidos!\n" + e);
        }
    }
}

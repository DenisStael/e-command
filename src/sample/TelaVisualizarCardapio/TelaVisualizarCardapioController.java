package sample.TelaVisualizarCardapio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConexaoBanco;
import sample.Prato;
import sample.TabelaPrato;
import sample.TabelaProduto;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaVisualizarCardapioController {
    ConexaoBanco conexaoBanco = new ConexaoBanco();
    TabelaProduto tabelaProduto = new TabelaProduto();
    TabelaPrato tabelaPrato = new TabelaPrato();

    private String sql = "select * from produto order by codproduto;"; //sql pra consulta
    private String sql2 = "select * from prato order by codprato;"; //String sql

    private List<Prato> listPrato = new ArrayList<>();
    private ObservableList<Prato> observableListPrato;

    @FXML
    private ListView<Prato> listviewPrato;
    private Button botaoVisualizarCardapio;





    //método que mostra a tabela com todos os pratos em estoque e
    //recebe como parâmetros a tabela que será apresentada, suas colunas e a string sql
    /*public void mostraLista(ListView listaPratos, String sql){
        try {
            Statement stmt = conexaoBanco.connection.createStatement();//cria declaração sql
            ResultSet rs = stmt.executeQuery(sql); //executa a declaração e armazena o resultado

            //enquanto há resultados na consulta, registra os pratos na lista
            while (rs.next()){
                this.listPrato.add(new Prato(rs.getString());
            }
            rs.close(); //fecha o resultset
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar pratos!\n"+e);
        }
    }

    public void carregarListaPratos() throws SQLException {
        PreparedStatement ps = conexaoBanco.connection.prepareStatement("SELECT p.nome, b.preco FROM bebida b, produto p WHERE b.codbebida = p.codproduto;");
        //observableListPrato = FXCollections.observableArrayList(Prato);
        listviewPrato.setItems(observableListPrato);

        /*PreparedStatement ps = conexaoBanco.connection.prepareStatement("SELECT p.nome, b.preco FROM produto p, bebida b WHERE b.codbebida = p.codproduto;");
        ps.setString();

        ps.executeUpdate();//Executa a declaração SQL
        JOptionPane.showMessageDialog(null, "Produto adicionado!");

    }*/

    public void initialize(URL url, ResourceBundle rb) throws SQLException {


    }
}

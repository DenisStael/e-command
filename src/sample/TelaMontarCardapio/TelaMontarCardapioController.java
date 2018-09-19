package sample.TelaMontarCardapio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConexaoBanco;
import sample.Main;
import sample.Prato;
import sample.Produto;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TelaMontarCardapioController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco();

    ObservableList<Prato> listaPratos = FXCollections.observableArrayList();

    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    private void adicionarPrato() {

    }

    private void adcionarBebida(){

    }

    private void adicionarComplemento(){

    }

    private void adicionarOpcional(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select nome,codprato,descricao,preco from produto;");
            while (rs.next()){
                listaPratos.add(new Prato(rs.getString("nome"),rs.getInt("codprato"),rs.getString("descricao"), rs.getFloat("preco")));
            }
            rs.close();
            colunaProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tabelaProdutos.setItems(listaProdutos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar produtos!\n"+e);
        }*/
    }
}

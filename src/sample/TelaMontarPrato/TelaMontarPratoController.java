package sample.TelaMontarPrato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import sample.ConexaoBanco;
import sample.Main;
import sample.Produto;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class TelaMontarPratoController implements Initializable {

    ConexaoBanco conexaoBanco = new ConexaoBanco(); //Objeto de conexão com o banco

    //Atributos da tela
    @FXML
    TextField txtNomePrato, txtPreco, txtAdicionaProduto;
    @FXML
    TextArea txtDescricao;
    @FXML
    TableColumn<Produto, String> colunaProd, colunaAddProd, colunaDescricao;
    @FXML
    TableColumn<Produto, Integer> colunaAddCod, colunaCod;
    @FXML
    TableView<Produto> tabelaProdutos, tabelaAddProdutos;
    @FXML
    Label labelProd;

    //Listas que armazenam objetos do tipo produto
    ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    ObservableList<Produto> listaAddProdutos = FXCollections.observableArrayList();

    //método voltat para tela anterior
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    //limpa os campos da tela
    public void acaoLimpar(){
        txtNomePrato.clear(); txtPreco.clear();
        txtDescricao.clear(); txtAdicionaProduto.clear();
        tabelaAddProdutos.getItems().clear(); labelProd.setText(null);
    }

    //Método de salvar prato no banco de dados
    public void acaoSalvar(){
        //Verifica se todos os campos estão preenchidos
        if(!txtNomePrato.getText().isEmpty() && !txtPreco.getText().isEmpty() && !txtDescricao.getText().isEmpty() && !listaAddProdutos.isEmpty()){
            try {
                //Cria declaração sql para inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement("insert into prato(nome,preco,descricao)values(?,?,?);");

                //insere os valores aos parâmetros da declaração sql
                ps.setString(1, txtNomePrato.getText());
                ps.setFloat(2, Float.parseFloat(txtPreco.getText())); //converte para float
                ps.setString(3, txtDescricao.getText());

                ps.executeUpdate(); //Executa declaração sql

                //cria nova declaração sem paramentros
                Statement stmt = conexaoBanco.connection.createStatement();

                //Consulta o último código de prato adicionado e armazena resultado da consulta
                ResultSet rs = stmt.executeQuery("select max(codprato) codMax from prato;");
                int codMax = 0;

                //verifica se há resultados
                if(rs.next()) {
                    codMax = rs.getInt("codMax"); //insere resultado convertido para inteiro em 'codMax'
                }
                rs.close();//fecha resultset

                //Cria nova declaração sql
                PreparedStatement ps2;

                //insere na tabela pratoProdutos os códigos do prato e dos produtos que foram adicionados na lista 'listaAddProdutos'
                for(int i = 0; i < listaAddProdutos.size(); i++){ //percorre a lista

                    //insere os valores aos parametros e executa a declaração sql
                    ps2 = conexaoBanco.connection.prepareStatement("insert into pratoprodutos(codprato,codproduto) values(?,?);");
                    ps2.setInt(1, codMax);
                    ps2.setInt(2, listaAddProdutos.get(i).getCodproduto());
                    ps2.executeUpdate();
                    ps2.close();
                }
                acaoLimpar();//Limpa os campos da tela
                JOptionPane.showMessageDialog(null, "Prato Cadastrado!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Digite apenas números!\n"+e); //mensagem de sucesso
            }
            //mensagem de erro
        }else JOptionPane.showMessageDialog(null, "Preencha todos os campos para adicionar!");
    }

    //método que insere os produtos na listaAddProdutos e na tabelaAddProdutos
    public void acaoAdicionaProduto(){
        labelProd.setText(null); //limpa o texto do label de aviso
        boolean prodIsDif = true; //booleano para verificar se o produto que está sendo iserido na lista já não está adicionado

        if(!txtAdicionaProduto.getText().isEmpty()){ //verifica se o campo de texto não está vazio
            try {
                int cod = Integer.parseInt(txtAdicionaProduto.getText());//converte o texto em inteiro e armazena em 'cod'

                //Percorre a lista e verifica se o produto já existe nela
                for(int i = 0; i < listaAddProdutos.size(); i++){
                    if(listaAddProdutos.get(i).getCodproduto() == cod){
                        labelProd.setText("Produto já adicionado!");
                        prodIsDif = false;
                    }
                }
                //verifica se é verdadeiro (produto é diferente)
                if(prodIsDif){

                    //Cria declaração sql, passa o valor no parametro
                    PreparedStatement ps = conexaoBanco.connection.prepareStatement("select nome,codproduto from produto where codproduto = ?;");
                    ps.setInt(1, cod);

                    //Executa a declaração sql e armazena resultado
                    ResultSet rs = ps.executeQuery();

                    //verifica se há resultados e adiciona o produto à lista
                    if(rs.next()){
                        listaAddProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto")));
                    } else labelProd.setText("Produto não encontrado!");

                    //atribui às colunas seus valores
                    colunaAddProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    colunaAddCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));

                    tabelaAddProdutos.setItems(listaAddProdutos);//adiciona produto à tabela
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Digite apenas números!\n"+e);
            }
        }
        txtAdicionaProduto.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*Pesquisa no banco os produtos que estão em estoque, armazena numa lista,
         atribui valores às colunas da tabela e adiciona os produtos à tabela*/
        try {
            Statement stmt = conexaoBanco.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select nome,codproduto,descricao from produto order by codproduto;");
            while (rs.next()){
                listaProdutos.add(new Produto(rs.getString("nome"), rs.getInt("codproduto"), rs.getString("descricao")));
            }
            rs.close();
            colunaProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCod.setCellValueFactory(new PropertyValueFactory<>("codproduto"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tabelaProdutos.setItems(listaProdutos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apresentar produtos!\n"+e);
        }
    }
}
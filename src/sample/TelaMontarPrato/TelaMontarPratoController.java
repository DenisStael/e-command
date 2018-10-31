package sample.TelaMontarPrato;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.ConexaoBanco;
import sample.Main;
import sample.TabelaLista;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class TelaMontarPratoController implements Initializable {

    private ConexaoBanco conexaoBanco = new ConexaoBanco(); //Objeto de conexão com o banco
    private TabelaLista tabelaPrato = new TabelaLista();
    private String sql = "select nome,codprato,descricao,preco from prato;";

    //Atributos da tela
    @FXML
    TextField txtNomePrato, txtPreco;
    @FXML
    TextArea txtDescricao;
    @FXML
    TableColumn colunaPratos, colunaCodigo, colunaDescricao, colunaPreco;
    @FXML
    TableView tabelaPratos;
    @FXML
    private RadioButton tipoComida, tipoBebida;

    //método voltat para tela anterior
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    //limpa os campos da tela
    public void acaoLimpar(){
        txtNomePrato.clear(); txtPreco.clear();
        txtDescricao.clear(); tipoBebida.setSelected(false);
        tipoComida.setSelected(false);
    }

    public void selecaoComida(){
        if(tipoBebida.isSelected()){
            tipoBebida.setSelected(false);
        }
    }

    public void selecaoBebida(){
        if(tipoComida.isSelected()){
            tipoComida.setSelected(false);
        }
    }

    //Método de salvar prato no banco de dados
    public void acaoSalvar(){
        //Verifica se todos os campos estão preenchidos
        if(!txtNomePrato.getText().isEmpty() && !txtPreco.getText().isEmpty() && !txtDescricao.getText().isEmpty() && (tipoBebida.isSelected() || tipoComida.isSelected())){
            try {
                //Cria declaração sql para inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement("insert into prato(nome,preco,descricao,tipo)values(?,?,?,?);");

                //insere os valores aos parâmetros da declaração sql
                ps.setString(1, txtNomePrato.getText());
                ps.setFloat(2, Float.parseFloat(txtPreco.getText())); //converte para float
                ps.setString(3, txtDescricao.getText());
                if(tipoComida.isSelected()){
                    ps.setString(4, "Comida");
                } else if(tipoBebida.isSelected()){
                    ps.setString(4, "Bebida");
                }

                ps.executeUpdate(); //Executa declaração sql

                acaoLimpar();//Limpa os campos da tela

                tabelaPrato.mostraTabelaPratos(tabelaPratos,colunaPratos,colunaDescricao,colunaCodigo,colunaPreco,sql);
                JOptionPane.showMessageDialog(null, "Prato Cadastrado!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato!\n"+e); //mensagem de sucesso
            }
            //mensagem de erro
        }else JOptionPane.showMessageDialog(null, "Preencha todos os campos para adicionar!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaPrato.mostraTabelaPratos(tabelaPratos,colunaPratos,colunaDescricao,colunaCodigo,colunaPreco,sql);
        tipoComida.setSelected(true);
    }
}
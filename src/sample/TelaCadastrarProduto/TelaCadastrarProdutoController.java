package sample.TelaCadastrarProduto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.ConexaoBanco;
import sample.Main;
import sample.Produto;
import sample.TabelaProduto;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TelaCadastrarProdutoController implements Initializable {

    private ConexaoBanco conexaoBanco = new ConexaoBanco();//Objeto de conexão com o banco
    private TabelaProduto tabelaProduto = new TabelaProduto();//Objeto tabela que mostra todos os produtos no banco
    private String sql = "select * from produto order by codproduto;"; //sql pra consulta

    //Atributos da tela
    @FXML
    private TextField txtQtdProduto, txtNomeProduto, txtUnidadeMedida; //caixas de texto
    @FXML
    private TableColumn colunaProd, colunaDescricao, colunaCod, colunaQuantidade, colunaMedida; //Colunas da tabela que serão preenchidas com atributos de produto
    @FXML
    private TableView tabelaProdutos; //Tabela que será mostrada na tela com os produtos cadastrados no banco
    @FXML
    private TextArea txtDescricao;

    //método voltar pra tela anterior
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");//método de trocar a scene
    }

    //método de cadastrar produto
    public void acaoCdProduto() {

        //checa se todos os campos foram preenchidos
        if(!txtNomeProduto.getText().isEmpty() && !txtDescricao.getText().isEmpty() && !txtQtdProduto.getText().isEmpty() && !txtUnidadeMedida.getText().isEmpty()){
            try {
                //Declaração SQL pra inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement
                        ("INSERT INTO Produto(nome, quantidade, descricao, medida) VALUES(?,?,?,?);");

                //Atribui os parâmetros e os valores à declaração SQL criada anteriormente
                ps.setString(1, txtNomeProduto.getText());
                ps.setFloat(2, Float.parseFloat(txtQtdProduto.getText()));
                ps.setString(3, txtDescricao.getText());
                ps.setString(4, txtUnidadeMedida.getText());

                ps.executeUpdate();//Executa a declaração SQL

                //Mensagem de Sucesso
                JOptionPane.showMessageDialog(null, "Produto Cadastrado!");

                //Chama o método mostraTabela da classe TabelaProduto para mostrar os produtos cadastrados no banco na tabela
                tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,colunaMedida,sql);
            } catch (Exception e) {
                //Mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!\nErro: " + e);
            }
            //Limpa os campos de texto
            acaoCancelar();

        //Mensagem caso os campos não tenham todos sidos preenchidos
        } else JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
    }

    //Método que limpa os campos de texto na tela
    public void acaoCancelar() {
        txtQtdProduto.clear();
        txtDescricao.clear();
        txtNomeProduto.clear();
        txtUnidadeMedida.clear();
    }

    //Método que é executado assim que a tela é exibida
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Chama o método mostraTabela da classe TabelaProduto para mostrar os produtos cadastrados no banco na tabela
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescricao,colunaCod,colunaQuantidade,colunaMedida,sql);
    }
}



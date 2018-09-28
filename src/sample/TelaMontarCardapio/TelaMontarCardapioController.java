package sample.TelaMontarCardapio;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class TelaMontarCardapioController implements Initializable {

    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private Produto tabelaProduto = new Produto();
    private Prato tabelaPrato = new Prato();
    private String sql = "select * from produto order by codproduto;"; //sql pra consulta
    private String sql2 = "select nome,codprato,descricao,preco from prato order by codprato;"; //String sql
    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    TableView<Prato> tabelaPratos;
    @FXML
    private TableColumn colunaProd, colunaDescProd, colunaPrato, colunaDescPrato;
    @FXML
    private TableColumn colunaCodProd, colunaQtdProd, colunaCodPrato, colunaPreco, colunaMedida;
    @FXML
    private TextField txtNomePrato,txtNomeProduto,txtCodPrato,txtCodProduto,txtPreco;

    @FXML //volta para a tela anterior
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }
    @FXML //pesquisa por nome e mostra na tabela apenas produtos correspondentes à pesquisa
    private void acaoPesquisarProduto(){
        String sqlPesquisa = "select * from produto where nome ilike '%"+txtNomeProduto.getText()+"%';";
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescProd,colunaCodProd,colunaQtdProd,colunaMedida,sqlPesquisa);
    }
    @FXML //pesquisa por nome e mostra na tabela apenas pratos correspondentes à pesquisa
    private void acaoPesquisarPrato(){
        String sqlPesquisa = "select * from prato where nome ilike '%"+txtNomePrato.getText()+"%';";
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sqlPesquisa);
    }
    @FXML //preenche o campo de texto com o código do prato selecionado ao clicar numa linha da tabela
    private void clicarTabelaPrato(){
        txtCodPrato.setText(tabelaPratos.getSelectionModel().getSelectedItem().getCodprato());
    }
    @FXML //preenche o campo de texto com o código do produto selecionado ao clicar numa linha da tabela
    private void clicarTabelaProduto(){
        txtCodProduto.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getCodproduto());
    }
    @FXML //chama a tela de visualizar cardápio
    private void acaoVisualizarCardapio() throws IOException {
        Main.trocaTela("TelaVisualizarCardapio/telaVisualizarCardapio.fxml");
    }

    public void acaoAdicionarPrato() {
       if(!txtCodPrato.getText().isEmpty()){
            try {
                //Declaração SQL pra inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement
                        ("UPDATE Prato SET cardapio = TRUE WHERE codprato = ?;");

                //Atribui os parâmetros e os valores à declaração SQL criada anteriormente
                ps.setInt(1, Integer.parseInt(txtCodPrato.getText()));

                ps.executeUpdate();//Executa a declaração SQL

                limpar(); //limpa os campos de texto
                //Mensagem de Sucesso
                JOptionPane.showMessageDialog(null, "Prato Cadastrado!");

            } catch (Exception e) {
                //Mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato!\nErro: " + e);
            }
        } else JOptionPane.showMessageDialog(null,"Digite o código do prato que deseja adicionar ao Cardápio!");
    }

    public void adicionarAoCardapio(String SQL) {
        if(!txtCodProduto.getText().isEmpty() && !txtPreco.getText().isEmpty()){
            try {
                //Declaração SQL pra inserção no banco
                PreparedStatement ps = conexaoBanco.connection.prepareStatement(SQL);

                //Atribui os parâmetros e os valores à declaração SQL passada por parâmetro
                ps.setInt(1, Integer.parseInt(txtCodProduto.getText()));
                ps.setFloat(2, Float.parseFloat(txtPreco.getText()));

                ps.executeUpdate();//Executa a declaração SQL

                limpar(); //limpa os campos de texto
                //Mensagem de Sucesso
                JOptionPane.showMessageDialog(null, "Produto adicionado!");

            } catch (Exception e) {
                //Mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao adicionar produto!\nErro: " + e);
            }
        } else JOptionPane.showMessageDialog(null,"Digite o código do produto e defina um preço!");
    }

    public void acaoAdicionarBebida() {
        adicionarAoCardapio("INSERT INTO Bebida(codbebida, preco, cardapio) VALUES(?,?,true);");
    }

    public void limpar(){
        txtPreco.clear(); txtCodPrato.clear(); txtCodProduto.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaProduto.mostraTabela(tabelaProdutos,colunaProd,colunaDescProd,colunaCodProd,colunaQtdProd,colunaMedida,sql);
        tabelaPrato.mostraTabela(tabelaPratos,colunaPrato,colunaDescPrato,colunaCodPrato,colunaPreco,sql2);
    }

}

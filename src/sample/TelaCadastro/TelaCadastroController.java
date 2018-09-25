package sample.TelaCadastro;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.ConexaoBanco;
import sample.Main;
import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TelaCadastroController {

    ConexaoBanco conexaoBanco = new ConexaoBanco(); //Objeto de conexão com o banco

    public static String tipo; //String que recebe da tela do Gerente o tipo de usuário que está sendo cadastrado

    //Atributos da Tela
    @FXML
    private TextField txtNome; //caixa de texto
    @FXML
    private PasswordField txtSenha, txtConfirmaSenha; //campo de senha

    //Método de voltar para a tela anterior
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml"); //Chama a tela
    }

    //Método de enviar cadastro de usuário ao banco
    public void acaoEnvioCadastro() {

        //Checa se os campos estão preenchidos
        if(!txtNome.getText().isEmpty() && !txtSenha.getText().isEmpty() && !txtConfirmaSenha.getText().isEmpty()){

            //Checa se as senhas digitadas correspondem
            if(txtSenha.getText().equals(txtConfirmaSenha.getText())){
                try {
                    //Declaração SQL de inserção no banco
                    PreparedStatement ps = conexaoBanco.connection.prepareStatement("insert into usuario(nome,senha,tipo)VALUES(?,?,?);");

                    //Insere os valores nos determinados parâmetros da declaração SQL
                    ps.setString(1, txtNome.getText());
                    ps.setString(2, txtSenha.getText());
                    ps.setString(3, tipo);

                    ps.executeUpdate(); //Executa a declaração SQL

                    JOptionPane.showMessageDialog(null, "Inserido com sucesso!"); //Mensagem de Sucesso
                } catch (SQLException e) {
                    //Mensagem de erro
                    JOptionPane.showMessageDialog(null, "Erro ao inserir os dados!\nErro: "+e);
                }
                //Limpa os campos de texto
                txtNome.clear(); txtSenha.clear(); txtConfirmaSenha.clear();
            } else {
                //Mensagem de erro na verificação das senhas
                JOptionPane.showMessageDialog(null, "As senhas são diferentes!");
            }
            //Mensagem de erro por haver campos vazios
        } else JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
    }

}

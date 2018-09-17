package sample;

import javax.swing.*;
import java.sql.*;

public class ConexaoBanco {
    private String url = "jdbc:postgresql://localhost:5432/ECommand";//Caminho da database PostgresSQL
    private String driver = "org.postgresql.Driver";//Driver do Postgres
    private String user = "postgres";//Usuário configurado no Postgres
    private String pass = "admin";//Senha de acesso ao Postgres
    public static Connection connection;//Variável de conexão do Banco

    public void conectaBanco(){
        System.setProperty("jdbc.Drivers", driver);//Configura o driver
        try {
            connection = DriverManager.getConnection(url, user, pass);//Informa o caminho pra database, usuario e senha

            //Mensagem na tela informando se a conexão foi bem sucedida
            JOptionPane.showMessageDialog(null, "Banco conectado com sucesso!");
        } catch (SQLException e) {
            //Mensagem informando que houve algum problema com a conexão
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco!\n Erro: "+e.getMessage());

            //Fecha a aplicação caso não haja conexão
            Main.stage.close();
        }
    }

    public void desconectaBanco(){
        try {
            connection.close();//Termina a coxexão com o banco
            JOptionPane.showMessageDialog(null, "Banco desconectado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar do Banco!\n Erro: "+e.getMessage());
        }

    }

}

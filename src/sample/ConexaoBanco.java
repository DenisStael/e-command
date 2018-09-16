package sample;

import javax.swing.*;
import java.sql.*;

public class ConexaoBanco {
    private String url = "jdbc:postgresql://localhost:5432/ECommand";
    private String driver = "org.postgresql.Driver";
    private String user = "postgres";
    private String pass = "admin";
    public static Connection connection;
    public Statement stmt;
    public ResultSet rs;

    public void conectaBanco(){
        System.setProperty("jdbc.Drivers", driver);
        try {
            connection = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "Banco conectado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco!\n Erro: "+e.getMessage());
            Main.stage.close();
        }
    }

    public void desconectaBanco(){
        try {
            connection.close();
            JOptionPane.showMessageDialog(null, "Banco desconectado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar do Banco!\n Erro: "+e.getMessage());
        }

    }

}

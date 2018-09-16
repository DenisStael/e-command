package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String url = "jdbc:postgresql://localhost:5432/ECommand";
    private static final String driver = "org.postgresql.Driver";
    private static final String user = "postgres";
    private static final String pass = "admin";

    public static void consultaBanco(String sql){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }
}

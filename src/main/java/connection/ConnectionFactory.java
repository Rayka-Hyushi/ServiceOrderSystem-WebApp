package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar propriedades de conexão", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o Banco de Dados: " + e);
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
            }
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
            }
        }
        closeConnection(con, stmt);
    }

}

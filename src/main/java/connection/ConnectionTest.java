package connection;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {
        Connection con = ConnectionFactory.getConnection();

        if (con != null) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Falha");
        }
    }

}

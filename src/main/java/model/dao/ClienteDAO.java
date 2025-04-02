package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cliente;

public class ClienteDAO {

    private Connection con = null;

    public ClienteDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create(Cliente cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into cliente (nome, telefone, email, endereco) values (?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Cliente> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from cliente order by idcliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return listaClientes;
    }

    public boolean update(Cliente cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update cliente set nome = ?, telefone = ?, email = ?, endereco = ? where idcliente = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getIdcliente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Cliente cliente) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from cliente where idcliente = ?");
            stmt.setInt(1, cliente.getIdcliente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}

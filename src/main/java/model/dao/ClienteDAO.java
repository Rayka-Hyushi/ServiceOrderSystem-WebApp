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

    public boolean create(Cliente cliente) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into cliente (nome, telefone, email, endereco, idusuario) values (?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Cliente> read(int idusuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from cliente where idusuario = ? order by idcliente");
            stmt.setInt(1, idusuario);
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
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update cliente set nome = ?, telefone = ?, email = ?, endereco = ? where idcliente = ? and idusuario = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getIdcliente());
            stmt.setInt(6, cliente.getIdusuario());
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
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from cliente where idcliente = ? and idusuario = ?");
            stmt.setInt(1, cliente.getIdcliente());
            stmt.setInt(2, cliente.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Cliente findById(int idcliente, int idusuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            stmt = con.prepareStatement("select * from cliente where idcliente = ? and idusuario = ?");
            stmt.setInt(1, idcliente);
            stmt.setInt(2, idusuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cliente;
    }
}

package model.dao;

import connection.ConnectionFactory;
import model.bean.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection con = null;

    public boolean create(Usuario usuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into usuario (nome, email, senha, foto_perfil) values (?, ?, ?, ?)");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setBytes(4, usuario.getFoto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return true;
    }

    public Usuario read(int id) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Usuario usuario = new Usuario();
        try {
            stmt = con.prepareStatement("select id, nome, email, senha, foto_perfil from usuario where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFoto(rs.getBytes("foto_perfil"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return null;
    }

    public boolean update(Usuario usuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update usuario set nome = ?, email = ?, senha = ?, foto_perfil = ? where id = ?");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setBytes(4, usuario.getFoto());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return true;
    }

    public boolean delete(int id) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from usuario where id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return true;
    }

    public Usuario login(String email, String password) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Usuario usuario = new Usuario();
        try {
            stmt = con.prepareStatement("select * from usuario where email = ? and senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFoto(rs.getBytes("foto_perfil"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return null;
    }

    public byte[] profilePhoto(int id) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("select foto_perfil from usuario where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getBytes("foto_perfil");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return null;
    }
}

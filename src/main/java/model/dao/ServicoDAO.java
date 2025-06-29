package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Servico;

public class ServicoDAO {

    private Connection con = null;

    public boolean create(Servico servico) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into servico (nome, descricao, valor, idusuario) values (?, ?, ?, ?)");
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.setInt(4, servico.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Servico> read(int idusuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Servico> listaServicos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select * from servico where idusuario = ? order by idservico");
            stmt.setInt(1, idusuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdservico(rs.getInt("idservico"));
                servico.setNome(rs.getString("nome"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setValor(rs.getDouble("valor"));
                servico.setIdusuario(rs.getInt("idusuario"));
                listaServicos.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return listaServicos;
    }

    public boolean update(Servico servico) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update servico set nome = ?, descricao = ?, valor = ? where idservico = ? and idusuario = ?");
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.setInt(4, servico.getIdservico());
            stmt.setInt(5, servico.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Servico servico) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from servico where idservico = ? and idusuario = ?");
            stmt.setInt(1, servico.getIdservico());
            stmt.setInt(2, servico.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}

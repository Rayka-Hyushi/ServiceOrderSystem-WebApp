package model.dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.OrdemCliente;
import model.bean.OrdemServico;
import model.bean.Servico;

public class OrdemServicoDAO {
    private Connection con = null;

    public boolean create(OrdemServico os) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into relacao_ordem_servico (osid, idservico) values (?, ?)");
            stmt.setInt(1, os.getOsid());
            stmt.setInt(2, os.getIdservico());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Servico> read(int osid) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Servico> listaServico = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select idservico, nome, descricao, valor from view_os where osid = ? order by idservico");
            stmt.setInt(1, osid);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Servico os = new Servico();
                os.setIdservico(rs.getInt("idservico"));
                os.setNome(rs.getString("nome"));
                os.setDescricao(rs.getString("descricao"));
                os.setValor(rs.getDouble("valor"));

                listaServico.add(os);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listaServico;
    }

    public boolean delete(OrdemServico os) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from relacao_ordem_servico where osid = ? and idservico = ?");
            stmt.setInt(1, os.getOsid());
            stmt.setInt(2, os.getIdservico());
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

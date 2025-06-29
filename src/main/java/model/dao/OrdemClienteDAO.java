package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.OrdemCliente;
import model.bean.Servico;

public class OrdemClienteDAO {

    private Connection con = null;

    public boolean create(OrdemCliente oc) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into ordens_servico (idcliente, descricao, valor_total, estado, desconto, extras, idusuario) values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, oc.getCliente().getIdcliente());
            stmt.setString(2, oc.getDesc());
            stmt.setDouble(3, oc.getTotal());
            stmt.setString(4, oc.getStatus());
            stmt.setDouble(5, oc.getDesconto());
            stmt.setDouble(6, oc.getExtras());
            stmt.setInt(7, oc.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<OrdemCliente> read(int idusuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<OrdemCliente> listaOs = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select osid, oscliente, nome, osdesc, total, estado, desconto, extras from view_oc where idusuario = ? order by osid desc");
            stmt.setInt(1, idusuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("oscliente"));
                cliente.setNome(rs.getString("nome"));

                OrdemCliente oc = new OrdemCliente();
                oc.setOsid(rs.getInt("osid"));
                oc.setDesc(rs.getString("osdesc"));
                oc.setTotal(rs.getDouble("total"));
                oc.setStatus(rs.getString("estado"));
                oc.setDesconto(rs.getDouble("desconto"));
                oc.setExtras(rs.getDouble("extras"));
                oc.setCliente(cliente);

                listaOs.add(oc);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listaOs;
    }

    public boolean update(OrdemCliente oc) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update ordens_servico set descricao = ?, valor_total = ?, desconto = ?, extras = ? where idcliente = ? and osid = ? and idusuario = ?");
            stmt.setString(1, oc.getDesc());
            stmt.setDouble(2, oc.getTotal());
            stmt.setDouble(3, oc.getDesconto());
            stmt.setDouble(4, oc.getExtras());
            stmt.setInt(5, oc.getCliente().getIdcliente());
            stmt.setInt(6, oc.getOsid());
            stmt.setInt(7, oc.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(OrdemCliente oc){
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("delete from ordens_servico where idcliente = ? and osid = ? and idusuario = ?");
            stmt.setInt(1, oc.getCliente().getIdcliente());
            stmt.setInt(2, oc.getOsid());
            stmt.setInt(3, oc.getIdusuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public OrdemCliente getOc(int osid, int idusuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrdemCliente oc = new OrdemCliente();
        try {
            stmt = con.prepareStatement("select valor_total, estado, desconto, extras from ordens_servico where osid = ? and idusuario = ?");
            stmt.setInt(1, osid);
            stmt.setInt(2, idusuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oc.setTotal(rs.getDouble("valor_total"));
                oc.setStatus(rs.getString("estado"));
                oc.setDesconto(rs.getDouble("desconto"));
                oc.setExtras(rs.getDouble("extras"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return oc;
    }

    public ArrayList<Servico> getServicos(int osid, int idusuario) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Servico> listaServico = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select nome, valor from view_os where osid = ? and idusuario = ? order by idservico");
            stmt.setInt(1, osid);
            stmt.setInt(2, idusuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Servico os = new Servico();
                os.setNome(rs.getString("nome"));
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

    public boolean setStatus(OrdemCliente oc) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update ordens_servico set estado = ? where idcliente = ? and osid = ? and idusuario = ?");
            stmt.setString(1, oc.getStatus());
            stmt.setInt(2, oc.getCliente().getIdcliente());
            stmt.setInt(3, oc.getOsid());
            stmt.setInt(4, oc.getIdusuario());
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

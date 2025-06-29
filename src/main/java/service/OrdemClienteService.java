package service;

import model.bean.Cliente;
import model.bean.OrdemCliente;
import model.bean.OrdemServico;
import model.bean.Servico;
import model.dao.ClienteDAO;
import model.dao.OrdemClienteDAO;
import model.dao.OrdemServicoDAO;
import model.dao.ServicoDAO;

import java.util.ArrayList;

public class OrdemClienteService {
    private OrdemClienteDAO ocd = new OrdemClienteDAO();

    public boolean create(Cliente cliente, String desc, double total, String status, double desconto, double extras) {
        OrdemCliente oc = new OrdemCliente();

        oc.setCliente(cliente);
        oc.setDesc(desc);
        oc.setTotal(total);
        oc.setStatus(status);
        oc.setDesconto(desconto);
        oc.setExtras(extras);

        return ocd.create(oc);
    }

    public ArrayList<OrdemCliente> read(int idusuario) {
        return ocd.read(idusuario);
    }

    public boolean mudarstatus(String status, int idcliente, int osid) {
        OrdemCliente oc = new OrdemCliente();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);

        oc.setStatus(status);
        oc.setCliente(cliente);
        oc.setOsid(osid);

        return ocd.setStatus(oc);
    }

    public boolean update(String desc, double total, int idcliente, int osid, double desconto, double extras) {
        OrdemCliente oc = new OrdemCliente();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);

        oc.setDesc(desc);
        oc.setTotal(total);
        oc.setCliente(cliente);
        oc.setOsid(osid);
        oc.setDesconto(desconto);
        oc.setExtras(extras);

        return ocd.update(oc);
    }

    public boolean apagar(int idcliente, int osid, int idusuario) {
        OrdemCliente oc = new OrdemCliente();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);

        oc.setCliente(cliente);
        oc.setOsid(osid);
        oc.setIdusuario(idusuario);

        return ocd.delete(oc);
    }

    public Cliente getCliente(int idcliente, int idusuario) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.findById(idcliente, idusuario);
    }

    public OrdemCliente getOc(int osid, int idusuario) {
        return ocd.getOc(osid, idusuario);
    }

    public ArrayList<Servico> getServicos(int osid, int idusuario) {
        return ocd.getServicos(osid, idusuario);
    }

    public ArrayList<Cliente> getClientes(int idusuario) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.read(idusuario);
    }

    public ArrayList<Servico> getTodosServicos(int idusuario) {
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.read(idusuario);
    }
}

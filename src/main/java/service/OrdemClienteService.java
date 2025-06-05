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
    public boolean create(Cliente cliente, String desc, double total, String status, double desconto, double extras) {
        OrdemCliente oc = new OrdemCliente();
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();

        oc.setCliente(cliente);
        oc.setDesc(desc);
        oc.setTotal(total);
        oc.setStatus(status);
        oc.setDesconto(desconto);
        oc.setExtras(extras);

        return ordemclienteDAO.create(oc);
    }

    public ArrayList<OrdemCliente> read() {
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();
        return ordemclienteDAO.read();
    }

    public Cliente getCliente(int idcliente) {
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();
        return ordemclienteDAO.getCliente(idcliente);
    }

    public OrdemCliente getOc(int osid) {
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();
        return ordemclienteDAO.getOc(osid);
    }

    public ArrayList<Servico> getServicos(int osid) {
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();
        return ordemclienteDAO.getServicos(osid);
    }

    public ArrayList<Cliente> getClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.read();
    }

    public ArrayList<Servico> getTodosServicos() {
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.read();
    }

    public boolean mudarstatus(String status, int idcliente, int osid) {
        OrdemCliente oc = new OrdemCliente();
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);

        oc.setStatus(status);
        oc.setCliente(cliente);
        oc.setOsid(osid);

        return ordemclienteDAO.finish(oc);
    }

    public boolean update(String desc, double total, int idcliente, int osid, double desconto, double extras) {
        OrdemCliente oc = new OrdemCliente();
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);

        oc.setDesc(desc);
        oc.setTotal(total);
        oc.setCliente(cliente);
        oc.setOsid(osid);
        oc.setDesconto(desconto);
        oc.setExtras(extras);

        return ordemclienteDAO.update(oc);
    }

    public boolean apagar(int idcliente, int osid) {
        OrdemCliente oc = new OrdemCliente();
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);

        oc.setCliente(cliente);
        oc.setOsid(osid);

        return ordemclienteDAO.apagar(oc);
    }
}

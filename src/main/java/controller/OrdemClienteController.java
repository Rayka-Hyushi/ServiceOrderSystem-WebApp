package controller;

import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.OrdemCliente;
import model.bean.Servico;
import model.dao.OrdemClienteDAO;

public class OrdemClienteController {
    
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
    
    public boolean apagar(int idcliente, int osid){
        OrdemCliente oc = new OrdemCliente();
        OrdemClienteDAO ordemclienteDAO = new OrdemClienteDAO();
        
        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);
        
        oc.setCliente(cliente);
        oc.setOsid(osid);
        
        return ordemclienteDAO.apagar(oc);
    }
}

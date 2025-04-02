package controller;

import java.util.ArrayList;
import model.bean.Cliente;
import model.dao.ClienteDAO;

public class ClienteController {
    
    public boolean create(String nome, String telefone, String email, String endereco) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        
        return clienteDAO.create(cliente);
    }
    
    public ArrayList<Cliente> read() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.read();
    }
    
    public boolean update(String nome, String telefone, String email, String endereco, int idcliente) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setIdcliente(idcliente);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        
        return clienteDAO.update(cliente);
    }
    
    public boolean delete(int idcliente) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setIdcliente(idcliente);
        
        return clienteDAO.delete(cliente);
    }
    
}

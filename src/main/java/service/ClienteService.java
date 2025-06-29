package service;

import model.bean.Cliente;
import model.dao.ClienteDAO;

import java.util.ArrayList;

public class ClienteService {
    ClienteDAO dao = new ClienteDAO();

    public boolean create(Cliente cliente) {
        return dao.create(cliente);
    }

    public ArrayList<Cliente> read(int idusuario) {
        return dao.read(idusuario);
    }

    public boolean update(Cliente cliente) {
        return dao.update(cliente);
    }

    public boolean delete(int id, int idusuario) {
        Cliente cliente = new Cliente();
        cliente.setIdcliente(id);
        cliente.setIdusuario(idusuario);
        return dao.delete(cliente);
    }

    public Cliente findById(int id, int idusuario) {
        return dao.findById(id, idusuario);
    }
}

package controller;

import java.util.ArrayList;
import model.bean.Cliente;
import model.bean.OrdemCliente;
import model.bean.OrdemServico;
import model.bean.Servico;
import model.dao.OrdemServicoDAO;

public class OrdemServicoController {
    
    public boolean create(int osid, int idservico) {
        OrdemServico os = new OrdemServico();
        OrdemServicoDAO ordemservicoDAO = new OrdemServicoDAO();
        
        os.setOsid(osid);
        os.setIdservico(idservico);
        
        return ordemservicoDAO.create(os);
    }
    
    public ArrayList<Servico> read(int osid) {
        OrdemServicoDAO ordemservicoDAO = new OrdemServicoDAO();
        return ordemservicoDAO.read(osid);
    }
    
    public boolean update(String desc, double total, int idcliente, int osid, double desconto, double extras) {
        OrdemCliente oc = new OrdemCliente();
        OrdemServicoDAO ordemservicoDAO = new OrdemServicoDAO();
        
        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);
        
        oc.setDesc(desc);
        oc.setTotal(total);
        oc.setCliente(cliente);
        oc.setOsid(osid);
        oc.setDesconto(desconto);
        oc.setExtras(extras);
        
        return ordemservicoDAO.update(oc);
    }
    
    public boolean delete(int osid, int idservico) {
        OrdemServico os = new OrdemServico();
        OrdemServicoDAO ordemservicoDAO = new OrdemServicoDAO();
        
        os.setOsid(osid);
        os.setIdservico(idservico);
        
        return ordemservicoDAO.delete(os);
    }
}

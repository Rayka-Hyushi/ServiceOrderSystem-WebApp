package controller;

import java.util.ArrayList;
import model.bean.Servico;
import model.dao.ServicoDAO;

public class ServicoController {
    
    public boolean create(String nome, String descricao, double valor) {
        Servico servico = new Servico();
        ServicoDAO servicoDAO = new ServicoDAO();
        
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        
        return servicoDAO.create(servico);
    }
    
    public ArrayList<Servico> read() {
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.read();
    }
    
    public boolean update(String nome, String descricao, double valor, int idservico) {
        Servico servico = new Servico();
        ServicoDAO servicoDAO = new ServicoDAO();
        
        servico.setIdservico(idservico);
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        
        return servicoDAO.update(servico);
    }
    
    public boolean delete(int idservico) {
        Servico servico = new Servico();
        ServicoDAO servicoDAO = new ServicoDAO();
        
        servico.setIdservico(idservico);
        
        return servicoDAO.delete(servico);
    }
    
}

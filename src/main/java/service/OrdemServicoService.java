package service;

import model.bean.Cliente;
import model.bean.OrdemCliente;
import model.bean.OrdemServico;
import model.bean.Servico;
import model.dao.OrdemServicoDAO;

import java.util.ArrayList;

public class OrdemServicoService {
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

    public boolean delete(int osid, int idservico) {
        OrdemServico os = new OrdemServico();
        OrdemServicoDAO ordemservicoDAO = new OrdemServicoDAO();

        os.setOsid(osid);
        os.setIdservico(idservico);

        return ordemservicoDAO.delete(os);
    }
}

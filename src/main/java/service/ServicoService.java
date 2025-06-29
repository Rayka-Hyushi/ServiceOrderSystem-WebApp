package service;

import model.bean.Servico;
import model.dao.ServicoDAO;

import java.util.ArrayList;

public class ServicoService {
    ServicoDAO dao = new ServicoDAO();

    public boolean create(String nome, String descricao, double valor, int idusuario) {
        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        servico.setIdusuario(idusuario);
        return dao.create(servico);
    }

    public ArrayList<Servico> read(int idusuario) {
        return dao.read(idusuario);
    }

    public boolean update(int id, String nome, String descricao, double valor, int idusuario) {
        Servico servico = new Servico();
        servico.setIdservico(id);
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        servico.setIdusuario(idusuario);
        return dao.update(servico);
    }

    public boolean delete(int id, int idusuario) {
        Servico servico = new Servico();
        servico.setIdservico(id);
        servico.setIdusuario(idusuario);
        return dao.delete(servico);
    }

    public Servico findById(int id, int idusuario) {
        for (Servico s : dao.read(idusuario)) {
            if (s.getIdservico() == id) return s;
        }
        return null;
    }
}

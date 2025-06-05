package service;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean cadastrar(Usuario usuario) {
        return usuarioDAO.create(usuario);
    }

    public Usuario buscar(int id) {
        return usuarioDAO.read(id);
    }

    public void alterar(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    public void excluir(int id) {
        usuarioDAO.delete(id);
    }

    public byte[] foto(int id) {
        return usuarioDAO.profilePhoto(id);
    }
}

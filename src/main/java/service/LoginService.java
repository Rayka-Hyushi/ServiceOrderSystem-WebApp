package service;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class LoginService {
    private final UsuarioDAO dao = new UsuarioDAO();

    public Usuario login(String username, String password) {
        return dao.login(username, password);
    }
}

package service;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final UsuarioDAO dao = new UsuarioDAO();

    public Usuario login(String email, String password) throws NoSuchAlgorithmException {
        return dao.login(email, password);
    }
}

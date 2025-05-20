package model.dao;

import model.bean.Usuario;

public class UsuarioDAO {
    public Usuario login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new Usuario(username, "Administrador");
        }
        return null;
    }
}

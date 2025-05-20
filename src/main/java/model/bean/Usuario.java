package model.bean;

public class Usuario {
    private String login;
    private String nome;

    public Usuario(String login, String nome) {
        this.login = login;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }
}

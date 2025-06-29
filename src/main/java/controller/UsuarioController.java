package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.bean.Usuario;
import service.UsuarioService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "inicio":
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
                break;
            case "cadastrar":
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/cadastrar.jsp");
                dispatcher.forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        Usuario usuario = null;
        if ("alterar".equals(action)) {
            UsuarioService usuarioService = new UsuarioService();
            HttpSession session = req.getSession();
            usuario = (Usuario) session.getAttribute("usuario");

            String nome = req.getParameter("username");
            System.out.println("username = " + nome);
            String email = criptografar(req.getParameter("email"));
            System.out.println("email = " + email);
            String senhaRaw = req.getParameter("password");
            System.out.println("password = " + senhaRaw);
            String senha = null;
            if (senhaRaw != null && !senhaRaw.isEmpty()) {
                senha = criptografar(senhaRaw);
            } else {
                senha = usuarioService.buscar(usuario.getId()).getSenha();
                System.out.println("senha = " + senha);
            }
            Part filePart = req.getPart("foto");
            byte[] imagem = null;
            if (filePart != null && filePart.getSize() > 0) {
                imagem = filePart.getInputStream().readAllBytes();
                System.out.println("foto = " + imagem);
            } else {
                imagem = usuario.getFoto(); // mantém foto atual se não enviar nova
            }

            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setFoto(imagem);

            usuarioService.alterar(usuario);

            resp.sendRedirect("home");
            return;
        } else if ("cadastrar".equals(action)) {
            String nome = req.getParameter("username");
            String email = criptografar(req.getParameter("email"));
            String password = criptografar(req.getParameter("password"));

            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(nome);
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(password);

            try {
                String caminhoImagem = getServletContext().getRealPath("./resources/images/default-profile.jpg");
                File file = new File(caminhoImagem);
                byte[] imagem = Files.readAllBytes(file.toPath());
                novoUsuario.setFoto(imagem);
            } catch (IOException e) {
                System.out.println("Erro ao carregar imagem padrão de perfil: " + e.getMessage());
                novoUsuario.setFoto(null);
            }

            if (usuarioService.cadastrar(novoUsuario)) {
                req.setAttribute("cadastro", true);
                resp.sendRedirect("index.jsp");
            } else {
                req.setAttribute("cadastro", false);
                resp.sendRedirect("/WEB-INF/pages/cadastrar.jsp");
            }
        }

    }

    private String criptografar(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashbytes = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashbytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }
}

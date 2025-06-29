package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.bean.Usuario;
import service.UsuarioService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@MultipartConfig
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
        System.out.println("Chegou no POST");
        req.getParameterMap().forEach((key, value) -> {
            System.out.println("Param: " + key + " -> " + String.join(",", value));
        });

        Usuario usuario = null;
        if ("alterar".equals(action)) {
            System.out.println("Action: " + action);

            HttpSession session = req.getSession();
            usuario = (Usuario) session.getAttribute("usuario");

            String nome = req.getParameter("username");
            System.out.println("username = " + nome);
            String email = req.getParameter("email");
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
            System.out.println("Antes de pegar a imagem");
            Part filePart = req.getPart("foto");
            System.out.println("Pegou a imagem");
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

            try {
                usuarioService.alterar(usuario);
                System.out.println("Alteração realizada com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().println("Erro ao alterar usuário: " + e.getMessage());
                return;
            }

            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        } else if ("cadastrar".equals(action)) {
            String nome = req.getParameter("username");
            String email = req.getParameter("email");
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

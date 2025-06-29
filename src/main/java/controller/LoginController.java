package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.bean.Usuario;
import service.LoginService;
import service.UsuarioService;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher dispatcher;

        if("logout".equals(action)) {
            HttpSession session = req.getSession(false);
            if(session != null) {
                session.invalidate();
            }
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } else if ("foto".equals(action)) {
            HttpSession session = req.getSession(false);
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            byte[] foto = usuario.getFoto();

            resp.setContentType("image/jpeg");
            resp.setContentLength(foto.length);
            resp.getOutputStream().write(foto);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = criptografar(req.getParameter("password"));

        Usuario usuario;
        try {
            usuario = loginService.login(email, password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", usuario);
            resp.sendRedirect("home");
        } else {
            req.setAttribute("error", 1);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
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

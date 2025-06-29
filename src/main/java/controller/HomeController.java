package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Usuario;
import service.UsuarioService;

import java.io.IOException;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        } else if (session.getAttribute("usuario") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Usuario atualizado = usuarioService.buscar(usuario.getId());
            session.setAttribute("usuario", atualizado);
        }
        req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
    }
}

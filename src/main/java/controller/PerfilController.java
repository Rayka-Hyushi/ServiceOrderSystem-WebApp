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

@WebServlet("/fotoPerfil")
public class PerfilController extends HttpServlet {
    private UsuarioService service = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        byte[] imagem = service.foto(usuario.getId());

        if (imagem != null) {
            resp.setContentType("image/jpeg");
            resp.setContentLength(imagem.length);
            resp.getOutputStream().write(imagem);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

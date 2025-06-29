package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Servico;
import model.bean.Usuario;
import service.ServicoService;

@WebServlet("/servicos")
public class ServicoController extends HttpServlet {
    private final ServicoService service = new ServicoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String action = req.getParameter("action");
        if (action == null) action = "";

        Servico servico;
        switch (action) {
            case "novo":
                req.setAttribute("modo", "novo");
                break;
            case "editar":
                int id = Integer.parseInt(req.getParameter("id"));
                servico = service.findById(id, usuario.getId());
                req.setAttribute("servico", servico);
                req.setAttribute("modo", "editar");
                break;
            case "excluir":
                int idExcluir = Integer.parseInt(req.getParameter("id"));
                service.delete(idExcluir, usuario.getId());
                break;
        }

        ArrayList<Servico> servicos = service.read(usuario.getId());
        session.setAttribute("servicos", servicos);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/servicos.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        String modo = req.getParameter("modo");

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        double valor = Double.parseDouble(req.getParameter("valor"));

        if ("novo".equals(modo)) {
            service.create(nome, descricao, valor, usuario.getId());
        } else if ("editar".equals(modo)) {
            int id = Integer.parseInt(req.getParameter("id"));
            service.update(id, nome, descricao, valor, usuario.getId());
        }

        resp.sendRedirect("servicos");
    }
}

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

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "novo":
                req.setAttribute("modo", "novo");
                break;
            case "editar":
                int id = Integer.parseInt(req.getParameter("id"));
                Servico servico = service.findById(id);
                req.setAttribute("servico", servico);
                req.setAttribute("modo", "editar");
                break;
            case "excluir":
                int idExcluir = Integer.parseInt(req.getParameter("id"));
                service.delete(idExcluir);
                break;
        }

        ArrayList<Servico> servicos = service.read();
        session.setAttribute("servicos", servicos);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/servicos.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String modo = req.getParameter("modo");

        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        double valor = Double.parseDouble(req.getParameter("valor"));

        if ("novo".equals(modo)) {
            service.create(nome, descricao, valor);
        } else if ("editar".equals(modo)) {
            int id = Integer.parseInt(req.getParameter("id"));
            service.update(id, nome, descricao, valor);
        }

        resp.sendRedirect("servicos");
    }
}

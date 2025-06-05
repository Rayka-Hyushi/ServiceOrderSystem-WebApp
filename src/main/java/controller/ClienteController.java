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
import model.bean.Cliente;
import service.ClienteService;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {
    private final ClienteService service = new ClienteService();

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
                int idEditar = Integer.parseInt(req.getParameter("idcliente"));
                Cliente cliente = service.findById(idEditar);
                req.setAttribute("clienteEdit", cliente);
                req.setAttribute("modo", "editar");
                break;
            case "excluir":
                int idExcluir = Integer.parseInt(req.getParameter("idcliente"));
                service.delete(idExcluir);
                break;
        }

        ArrayList<Cliente> clientes = service.read();
        session.setAttribute("clientes", clientes);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/clientes.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String modo = req.getParameter("modo");
        String idStr = req.getParameter("idcliente");
        String nome = req.getParameter("nome");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String endereco = req.getParameter("endereco");

        System.out.println(modo + " " + idStr + " " + nome + " " + telefone + " " + email + " " + endereco);

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);

        if ("novo".equals(modo)) {
            service.create(cliente);
        } else if ("editar".equals(modo)) {
            cliente.setIdcliente(Integer.parseInt(idStr));
            service.update(cliente);
        }

        resp.sendRedirect("clientes");
    }
}

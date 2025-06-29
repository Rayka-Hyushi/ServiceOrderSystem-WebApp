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
import model.bean.OrdemCliente;
import service.ClienteService;
import service.OrdemClienteService;
import service.OrdemServicoService;

@WebServlet("/ordens")
public class OrdemClienteController extends HttpServlet {
    private final OrdemClienteService service = new OrdemClienteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String action = req.getParameter("action");
        int idusuario = Integer.parseInt(req.getParameter("idusuario"));

        if (action == null) action = "";

        switch (action) {
            case "novo":
                req.setAttribute("modo", "novo");
                req.setAttribute("clientes", service.getClientes(idusuario));
                break;
            case "editar":
                int idOc = Integer.parseInt(req.getParameter("osid"));
                int idCliente = Integer.parseInt(req.getParameter("cliente"));
                OrdemCliente ordemCliente = service.getOc(idOc, idusuario);
                Cliente cliente = service.getCliente(idCliente, idusuario);
                ordemCliente.setCliente(cliente);
                req.setAttribute("ordemClienteEdit", ordemCliente);
                req.setAttribute("modo", "editar");
                break;
            case "excluir":
                idOc = Integer.parseInt(req.getParameter("osid"));
                idCliente = Integer.parseInt(req.getParameter("cliente"));
                service.apagar(idCliente, idOc, idusuario);
                break;
        }

        ArrayList<OrdemCliente> ordens = service.read(idusuario);
        session.setAttribute("ordens", ordens);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/ordens.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idcliente = Integer.parseInt(req.getParameter("idcliente"));
        String desc = req.getParameter("desc");
        double total = Double.parseDouble(req.getParameter("total"));
        String status = req.getParameter("status");
        double desconto = Double.parseDouble(req.getParameter("desconto"));
        double extras = Double.parseDouble(req.getParameter("extras"));

        OrdemCliente ordem = new OrdemCliente();
        ordem.setDesc(desc);
        ordem.setTotal(total);
        ordem.setStatus(status);
        ordem.setDesconto(desconto);
        ordem.setExtras(extras);

        Cliente cliente = new Cliente();
        cliente.setIdcliente(idcliente);
        ordem.setCliente(cliente);

        String osidStr = req.getParameter("osid");
        if (osidStr != null && !osidStr.isEmpty()) {
            ordem.setOsid(Integer.parseInt(osidStr));
            service.update(ordem.getDesc(), ordem.getTotal(), ordem.getCliente().getIdcliente(), ordem.getOsid(), ordem.getDesconto(), ordem.getExtras());
        } else {
            service.create(ordem.getCliente(), ordem.getDesc(), ordem.getTotal(), ordem.getStatus(), ordem.getDesconto(), ordem.getExtras());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/ordens.jsp");
        dispatcher.forward(req, resp);
    }
}

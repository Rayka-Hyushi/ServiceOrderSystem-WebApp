package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Usuario;
import service.LoginService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final LoginService service = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Usuario usuario = service.login(username, password);

        RequestDispatcher dispatcher;

        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", usuario);
            dispatcher = req.getRequestDispatcher("WEB-INF/pages/home.jsp");
            dispatcher.forward(req, resp);
        } else {
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

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
        } else {
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

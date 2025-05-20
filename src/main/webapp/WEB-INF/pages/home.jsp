<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="model.bean.Usuario" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if(usuario == null){
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service Order System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>
    <body class="bg-dark text-white text-center">
        <div class="container-fluid d-flex justify-content-between p-3 flex-wrap">
            <!-- Ícones -->
            <div class="d-flex flex-column">
                <div class="icon mb-3" onclick="window.location.href='servicos.jsp'">
                    <img src="${pageContext.request.contextPath}/resources/icons/servicos.png" alt="Serviços" class="img-fluid">
                    <p>Serviços</p>
                </div>

                <div class="icon mb-3" onclick="window.location.href='clientes.jsp'">
                    <img src="${pageContext.request.contextPath}/resources/icons/cliente.png" alt="Clientes" class="img-fluid">
                    <p>Clientes</p>
                </div>

                <div class="icon mb-3" onclick="window.location.href='ordens.jsp'">
                    <img src="${pageContext.request.contextPath}/resources/icons/servicossolicitados.png" alt="Ordens" class="img-fluid">
                    <p>Ordens</p>
                </div>
            </div>

            <div class="logout-icon text-center" onclick="window.location.href='login?action=logout'">
                <img src="${pageContext.request.contextPath}/resources/icons/sair.png" alt="Sair" class="img-fluid">
                <p>Sair</p>
            </div>
        </div>


        <!-- Texto de boas-vindas -->
        <div class="welcome position-absolute top-50 start-50 translate-middle">
            <h2>Bem-Vindo, <%= usuario.getNome() %>, ao</h2>
            <h1>Service Order System</h1>
        </div>

        <!-- Rodapé -->
        <footer class="position-absolute bottom-0 end-0 p-2 text-white small">
            <p class="mb-0">All Copyrights © Reserved - Created by Sidnei dos Santos</p>
            <p class="mb-0">Graduated in Computer Technician on January 12, 2024.</p>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    </body>
</html>

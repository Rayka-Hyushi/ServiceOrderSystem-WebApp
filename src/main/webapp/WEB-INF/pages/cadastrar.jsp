<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service Order System - Cadastro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
    </head>
    <body class="bg-dark text-white d-flex align-items-center justify-content-center vh-100">
        <form action="usuario" method="post" class="bg-secondary p-4 rounded shadow">
            <h2 class="text-center mb-3">Cadastro de Usuário</h2>
            <input type="hidden" name="action" value="cadastrar">

            <div class="mb-3">
                <label for="username">Usuário: </label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="mb-3">
                <label for="email">E-mail: </label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="password">Senha: </label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <c:if test="${cadastro == false}">
                <div class="alert alert-danger mt-2">Falha ao cadastrar usuário. E-mail já cadastrado ou falha na conexão com o banco de dados. Consulte o suporte para mais detalhes.</div>
            </c:if>

            <div class="d-flex justify-content-between mt-3">
                <a href="usuario?action=inicio" class="btn btn-danger me-2 w-50">Cancelar</a>
                <button type="submit" class="btn btn-primary w-50">Cadastrar</button>
            </div>
        </form>

        <!-- Rodapé -->
        <footer class="position-absolute bottom-0 end-0 p-2 text-white small">
            <p class="mb-0">All Copyrights © Reserved - Created by Sidnei dos Santos</p>
            <p class="mb-0">Graduated in Computer Technician on January 12, 2024.</p>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    </body>
</html>

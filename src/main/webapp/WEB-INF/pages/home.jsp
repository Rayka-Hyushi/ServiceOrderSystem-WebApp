<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="model.bean.Usuario" %>
<%@ page session="true" %>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect url="../../index.jsp"/>
</c:if>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service Order System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>
    <body class="bg-dark text-white text-center">
        <!-- Topo: Foto + Dropdown -->
        <div class="position-fixed p-3 end-0">
            <div class="dropdown">
                <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown">
                    <img src="login?action=foto" alt="Foto de Perfil" class="rounded-circle me-2" width="40" height="40">
                    <strong>${usuario.nome}</strong>
                </a>
                <ul class="dropdown-menu dropdown-menu-end text-small">
                    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modalFoto">Editar Perfil</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="login?action=logout">Sair</a></li>
                </ul>
            </div>
        </div>

        <div class="d-flex justify-content-center align-items-center flex-column text-center min-vh-100">
            <!-- Texto de boas-vindas -->
            <div class="mb-4">
                <h2>Bem-Vindo, ${usuario.nome}</h2>
                <h4>ao</h4> <h1>Service Order System</h1>
            </div>

            <!-- Botões Principais -->
            <div class="container">
                <div class="row justify-content-center gap-3">
                    <div class="col-md-3">
                        <a href="servicos" class="btn btn-outline-light btn-lg w-100 d-flex align-items-center justify-content-center gap-2">
                            <img src="${pageContext.request.contextPath}/resources/icons/servicos.png" alt="Serviços" class="img-fluid">
                            Serviços
                        </a>
                    </div>

                    <div class="col-md-3">
                        <a href="clientes" class="btn btn-outline-light btn-lg w-100 d-flex align-items-center justify-content-center gap-2">
                            <img src="${pageContext.request.contextPath}/resources/icons/cliente.png" alt="Clientes" class="img-fluid">
                            Clientes
                        </a>
                    </div>

                    <div class="col-md-3">
                        <a href="ordens" class="btn btn-outline-light btn-lg w-100 d-flex align-items-center justify-content-center gap-2">
                            <img src="${pageContext.request.contextPath}/resources/icons/servicossolicitados.png" alt="Ordens" class="img-fluid">
                            Ordens
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Alterar Foto -->
        <div class="modal fade" id="modalFoto" tabindex="-1" aria-labelledby="modalFotoLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form action="usuario" method="post" enctype="multipart/form-data" class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalFotoLabel">Meu Perfil</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
                    </div>

                    <div class="modal-body">
                        <div class="mb-3 text-start">
                            <input type="hidden" name="action" value="alterar">
                        </div>

                        <div class="mb-3 text-start">
                            <label for="username" class="form-label">Nome</label>
                            <input type="text" class="form-control" id="username" name="username" value="${usuario.nome}" required>
                        </div>

                        <div class="mb-3 text-start">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" value="${usuario.email}" required>
                        </div>

                        <div class="mb-3 text-start">
                            <label for="password" class="form-label">Senha</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Digite a nova senha se desejar">
                        </div>

                        <div class="mb-3 text-start">
                            <label for="foto" class="form-label">Foto</label>
                            <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Rodapé -->
        <footer class="position-absolute bottom-0 end-0 p-2 text-white small">
            <p class="mb-0">All Copyrights © Reserved - Created by Sidnei dos Santos</p>
            <p class="mb-0">Graduated in Computer Technician on January 12, 2024.</p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    </body>
</html>

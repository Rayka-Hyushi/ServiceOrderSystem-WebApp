<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Order System</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
</head>
<body>
<div class="desktop">
    <!-- Ícones -->
    <div class="icon" onclick="window.location.href='servicos.jsp'">
        <img src="../../resources/icons/servicos.png" alt="Serviços">
        <p>Serviços</p>
    </div>

    <div class="icon" onclick="window.location.href='clientes.jsp'">
        <img src="../../resources/icons/cliente.png" alt="Clientes">
        <p>Clientes</p>
    </div>

    <div class="icon" onclick="window.location.href='ordens.jsp'">
        <img src="../../resources/icons/servicossolicitados.png" alt="Ordens">
        <p>Ordens</p>
    </div>

    <div class="icon" onclick="window.location.href='logout.jsp'">
        <img src="../../resources/icons/sair.png" alt="Sair">
        <p>Sair</p>
    </div>
</div>

<!-- Texto de boas-vindas -->
<div class="welcome">
    <h2>Bem-Vindo ao</h2>
    <h1>Service Order System</h1>
</div>

<!-- Rodapé -->
<footer>
    <p>All Copyrights © Reserved - Created by Sidnei dos Santos</p>
    <p>Graduated in Computer Technician on January 12, 2024.</p>
</footer>
</body>
</html>

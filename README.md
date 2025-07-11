Service Order System - WebApp
Este projeto é um sistema web de Ordens de Serviço desenvolvido em Java, utilizando Maven, Tomcat e banco de dados PostgreSQL.

A aplicação permite o cadastro e gerenciamento de clientes e serviços e ordens de serviço de forma simples e eficiente.

Tecnologias Utilizadas
Java 21
Maven
Tomcat 10
PostgreSQL
Docker & Docker Compose

Execução com Docker
Para facilitar o deploy da aplicação em ambiente virtualizado, foram criados um Dockerfile e um docker-compose.yaml que permitem:

✅ Clonar o projeto diretamente do GitHub dentro do container 
✅ Realizar o build do projeto automaticamente 
✅ Subir o banco de dados PostgreSQL 
✅ Expor o sistema na porta 8080 
✅ Mapear os logs do Tomcat para a máquina host 

Como Executar
Pré-requisitos: Docker desktop instalado e em execução, além de alguma ferramenta capaz de se conectar ao banco postgres.

Baixe o Dockerfile, docker-compose.yaml e sos_db.sql na release mais recente e coloque-os em alguma pasta que você queira que seja salvo os logs do tomcat
Download aqui: https://github.com/Rayka-Hyushi/ServiceOrderSystem-WebApp/releases

Abra o terminal (PowerShell, cmd ou outro) e navegue até a pasta onde você deixou os arquivos baixados

Execute o comando: docker-compose up -d --build

Banco de Dados
O Docker Compose já configura automaticamente o banco de dados PostgreSQL com as seguintes credenciais:
Host: localhost 
Port: 5432
Username: postgres 
Password: 1234 

Após todos os containers necessários estiverem rodando, acesse o banco de dados pelo pgadmin ou outra ferramenta que possa executar o script presente no arquivo sos_db.sql.

Com os containers prontos e tabelas do banco criadas, acesse a aplicação no navegador pelo endereço que por padrão é: http://localhost:8080

Observação
Este projeto atende aos requisitos de ambiente virtualizado solicitados na avaliação de Sistemas Operacionais, com foco no funcionamento do sistema via containers Docker. Além disso, o sistema não está 100% pronto, pois, a pedido de outro professor ele está sendo adaptado para usar spring boot e hibernate em sua versão final, portanto a funcionalidade de ordens de serviço não foi implementada.

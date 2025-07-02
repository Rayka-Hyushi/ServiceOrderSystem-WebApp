FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Instalar o Git
RUN apt-get update && apt-get install -y git

# Clonar o projeto
RUN git clone https://github.com/Rayka-Hyushi/ServiceOrderSystem-WebApp.git .

# Compilar o projeto
RUN mvn clean package -DskipTests

LABEL authors="Rayka"

FROM tomcat:10.1.24-jdk21

# Limpa os apps padrões do tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o .war gerado
COPY --from=build /app/target/ServiceOrderSystem.war /usr/local/tomcat/webapps/ROOT.war

# Expor a porta 8080
EXPOSE 8080

# Volume para os logs
VOLUME [ "/usr/local/tomcat/logs" ]

# Comando de inicialização
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh", "run"]
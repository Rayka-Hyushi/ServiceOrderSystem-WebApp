package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.SQLException;
import model.bean.Cliente;
import model.bean.OrdemCliente;
import model.bean.Servico;
import connection.ConnectionFactory;
import java.sql.ResultSet;
import mail.EmailSender;

public class MailController {

    public StringBuilder mailbuilder = new StringBuilder();
    private String body = "";
    private Connection con = null;
    private int totalOrdens;
    public static final int ERRO_INTERNO = 500;
    public static final int SUCESSO = 200;

    public MailController() {
        con = ConnectionFactory.getConnection();
    }

    public int contaOrdens(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int total = 0;

        try {
            stmt = con.prepareStatement("SELECT c.idcliente AS idcliente, c.nome, COUNT(os.osid) AS total_ordens FROM cliente c JOIN ordens_servico os ON c.idcliente = os.idcliente WHERE c.idcliente = ? GROUP BY c.idcliente, c.nome;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total_ordens");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return total;
    }

    public int MailController(Cliente cliente, ArrayList<Servico> lista, OrdemCliente oc, int totalOrdens) {
        if (totalOrdens < 2 && oc.getStatus().equals("Pagamento Pendente")) {
            mailbuilder.setLength(0);
            mailbuilder.append(String.format("Olá, %s!👋\n", cliente.getNome()))
                    .append("Assistente de emails pessoal de Sidnei, Lumi, falando. Prazer em conhece-lo(a)! 🙋‍♀️️\n")
                    .append("Primeiramente gostariamos de agradecer por contar com nosso serviço, para cuidar do seu\n")
                    .append("dispositivo. Nossa prioridade é fornecer o melhor atendimento possível, além de sempre sermos\n")
                    .append("transparentes e sinceros com o(a) cliente. Você é Bem-Vindo(a) para solicitar nosso auxílio sempre que precisar!😁\n")
                    .append("Além disso, gostaria de lhe informar que o seu primeiro serviço solicitado foi completado com sucesso!🥳\n")
                    .append("Abaixo estão os detalhes da ordem.👇\n\n")
                    .append("Serviços realizados:\n");
            int i = 1;
            for (Servico s : lista) {
                mailbuilder.append(String.format("%d - %s: R$ %.2f✅\n", i, s.getNome(), s.getValor()));
                i++;
            }
            mailbuilder.append("+---------------------------------------------------------------------------------------+\n")
                    .append(String.format("-Desconto: R$ %.2f\n", oc.getDesconto()))
                    .append(String.format("+Extras: R$ %.2f\n", oc.getExtras()))
                    .append(String.format("=Total a Pagar: R$ %.2f💲\n\n", oc.getTotal()))
                    .append("Por favor dirija-se à nossa assistência para realizar o pagamento e retirar seu dispositivo\n")
                    .append("assim que possível. Agradecemos por contar conosco!\n\n")
                    .append("Atenciosamente,\n\n")
                    .append("Lumi - Assistente de E-mails e Sidnei - Técnico de Informática.\n\n")
                    .append("Aviso: Para garantir sua segurança e satisfação, verifique sempre a autenticidade deste e-mail\n")
                    .append("e entre em contato diretamente conosco para quaisquer dúvidas!🕵️‍♀️\n")
                    .append("📞Whats: +55 55 9108-2678\n\n")
                    .append("Mensagem gerada pelo sistema automático de e-mails de Sidnei.\n")
                    .append("S: Apresento a vocês a Lumi, minha assistente de e-mails.\n")
                    .append("Lumi: Novamente, é um prazer conhece-lo(a)!😊👍");

            body = mailbuilder.toString();
            return SUCESSO;
        } else if (totalOrdens >= 2 && oc.getStatus().equals("Pagamento Pendente")) {
            mailbuilder.setLength(0);
            mailbuilder.append(String.format("Olá, %s!\n", cliente.getNome()))
                    .append("Lumi falando!🙋‍♀️\n")
                    .append("Gostaria de lhe informar que o seu serviço solicitado foi completado com sucesso!🥳\n")
                    .append("Abaixo estão os detalhes da ordem.👇\n\n")
                    .append("Serviços realizados:\n");
            int i = 1;
            for (Servico s : lista) {
                mailbuilder.append(String.format("%d - %s: R$ %.2f✅\n", i, s.getNome(), s.getValor()));
                i++;
            }
            mailbuilder.append("+---------------------------------------------------------------------------------------+\n")
                    .append(String.format("(-)Desconto: R$ %.2f\n", oc.getDesconto()))
                    .append(String.format("(+)Extras: R$ %.2f\n", oc.getExtras()))
                    .append(String.format("(=)Total a Pagar: R$ %.2f💲\n\n", oc.getTotal()))
                    .append("Por favor dirija-se à nossa assistência para realizar o pagamento e retirar seu dispositivo\n")
                    .append("assim que possível. Agradecemos por contar conosco!\n\n")
                    .append("Atenciosamente,\n\n")
                    .append("Lumi - Assistente de E-mails e Sidnei - Técnico de Informática.\n\n")
                    .append("Aviso: Para garantir sua segurança e satisfação, verifique sempre a autenticidade deste e-mail\n")
                    .append("e entre em contato diretamente conosco para quaisquer dúvidas!🕵️‍♀\n")
                    .append("📞Whats: +55 55 9108-2678\n\n")
                    .append("Mensagem gerada pela assistente de e-mails, Lumi, de Sidnei.");

            body = mailbuilder.toString();
            return SUCESSO;
        } else if (oc.getStatus().contains("Finalizado") && totalOrdens < 2) {
            mailbuilder.setLength(0);
            mailbuilder.append(String.format("Olá, %s!\n", cliente.getNome()))
                    .append("Lumi aqui!😊👍️\n")
                    .append("Verificamos que o pagamento da sua primeira ordem de serviço foi confirmado,\n")
                    .append("logo, sua ordem de serviço foi marcada como Finalizada.🥳\n")
                    .append("Agradecemos por contar com nosso serviço.\n")
                    .append("Conte conosco sempre que precisar e se encontrar alguém precisando de ajuda\n")
                    .append("com o celular, tablet, notebook ou pc, seria ótimo se fala-se sobre nosso serviço.\n")
                    .append("Agradecemos por todo apoio e confiança. Desejamos a você um ótimo dia!🤝\n\n")
                    .append("Atenciosamente,\n\n")
                    .append("Lumi - Assistente de E-mails e Sidnei - Técnico de Informática.\n\n")
                    .append("Aviso: Para garantir sua segurança e satisfação, verifique sempre a autenticidade deste e-mail\n")
                    .append("e entre em contato diretamente conosco para quaisquer dúvidas!🕵️‍♀️\n")
                    .append("📞Whats: +55 55 9108-2678\n\n")
                    .append("Mensagem gerada pela assistente de e-mails, Lumi, de Sidnei.");

            body = mailbuilder.toString();
            return SUCESSO;
        } else if (oc.getStatus().contains("Finalizado")) {
            mailbuilder.setLength(0);
            mailbuilder.append(String.format("Olá, %s!\n", cliente.getNome()))
                    .append("Lumi aqui!😊👍️\n")
                    .append("Verificamos que o pagamento da sua ordem de serviço foi confirmado,\n")
                    .append("logo, sua ordem de serviço foi marcada como Finalizada.🥳\n")
                    .append("Agradecemos por contar com nosso serviço.\n")
                    .append("Conte conosco sempre que precisar.\n")
                    .append("Agradecemos por todo apoio e confiança. Desejamos a você um ótimo dia!🤝\n\n")
                    .append("Atenciosamente,\n\n")
                    .append("Lumi - Assistente de E-mails e Sidnei - Técnico de Informática.\n\n")
                    .append("Aviso: Para garantir sua segurança e satisfação, verifique sempre a autenticidade deste e-mail\n")
                    .append("e entre em contato diretamente conosco para quaisquer dúvidas!🕵️‍♀️\n")
                    .append("📞Whats: +55 55 9108-2678\n\n")
                    .append("Mensagem gerada pela assistente de e-mails, Lumi, de Sidnei.");

            body = mailbuilder.toString();
            return SUCESSO;
        } else {
            return ERRO_INTERNO;
        }
    }

    public int send(Cliente cliente, OrdemCliente oc, ArrayList<Servico> lista) {
        totalOrdens = contaOrdens(cliente.getIdcliente());
        int result = MailController(cliente, lista, oc, totalOrdens);
        System.out.println("Resultado do MailController: " + result);
        if (MailController(cliente, lista, oc, totalOrdens) == 0) {
            if (oc.getStatus().equals("Pagamento Pendente")) {
                return EmailSender.sendEmail(cliente.getEmail(), "Serviço Finalizado!", body);
            } else {
                return EmailSender.sendEmail(cliente.getEmail(), "Obrigado Pela Preferência!", body);
            }
        } else {
            return ERRO_INTERNO;
        }
    }
}

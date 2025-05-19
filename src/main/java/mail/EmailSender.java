package mail;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(EmailSender.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar propriedades de email", e);
        }
    }

    public static int sendEmail(String to, String subject, String body) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        properties.getProperty("mail.smtp.user"),
                        properties.getProperty("mail.smtp.password")
                );
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");
            return 0;
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            return -2;
        }
    }
}

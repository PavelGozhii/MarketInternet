package service;

import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {
    private static final Logger logger = Logger.getLogger(EmailSender.class);

    public static boolean sendMessage(String receiver, String text) {
        try {
            final Properties properties = new Properties();
            properties.load(EmailSender.class.getClassLoader().getResourceAsStream("mail.properties"));
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("pavelgozhii@gmail.com"));
            message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiver)});
            message.setSubject("InternetMarket");
            message.setText(text);
            Transport tr = mailSession.getTransport();
            tr.connect("internetmailmarket@gmail.com", "internetmarket.com123");
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            logger.debug("Sending message is successful");
            return true;
        } catch (IOException | MessagingException e) {
            logger.error("SendMessageError", e);
            return false;
        }
    }

}

package web.mentor.ru;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import web.mentor.ru.exceptions.MailException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailService {
    private final static Logger logger = Logger.getLogger(EmailService.class);

    public static void sendEmail(String smtp, String from, String password, String to, String subject, String text) {

        try {
            if(StringUtils.isEmpty(smtp)){
                throw new MailException("Parameter smtp is required");
            }else if(StringUtils.isEmpty(from)){
                throw new MailException("Parameter \"from\" e-mail is required");
            }else if(StringUtils.isEmpty(password)){
                throw new MailException("Parameter \"password\" of e-mail is required");
            }else if(StringUtils.isEmpty(to)){
                throw new MailException("Parameter \"to\" e-mail is required");
            }

            Properties properties = new Properties();

            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.smtps.host", from);
            properties.put("mail.smtps.auth", "true");
            properties.put("mail.smtp.sendpartial", "true");

            Session session = Session.getDefaultInstance(properties);

            try {
                logger.info("E-mail sending from " + from + " to " + to);
                Transport transport = session.getTransport();
                transport.connect(smtp, 465, from, password);

                MimeMessage message = new MimeMessage(session);
                message.setSubject(subject);
                message.setText(text);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSentDate(new Date());

                transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                logger.info("E-mail sent successfully");
            } catch (NoSuchProviderException e) {
                logger.error(e);
            } catch (MessagingException e) {
                logger.error(e);
            }
        } catch (MailException e) {
            logger.error(e);
        }
    }
}

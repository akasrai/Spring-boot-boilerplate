package com.springrestapi.boilerplate.mail;

import com.springrestapi.boilerplate.user.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MailService {
    private final Configuration templates;

    private final MailProperties mailProperties;

    @Autowired
    MailService(MailProperties mailProperties, Configuration templates){
        this.mailProperties = mailProperties;
        this.templates = templates;
    }


    public boolean sendVerificationMail(User user, String verificationCode) {
        String subject = "Please verify your email";
        String body = "";

        try {
            Template t = templates.getTemplate("email-verification.ftl");
            Map<String, String> map = new HashMap<>();
            map.put("VERIFICATION_URL", mailProperties.getVerificationApi() + verificationCode);
            map.put("USER_NAME", user.getFullName());
            body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return sendMail(user.getEmail(), subject, body);
    }

    public boolean sendMail(String toEmail, String subject, String body) {

        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", mailProperties.getPort());
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getFrom(), mailProperties.getFromName()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            transport.connect(mailProperties.getHost(), mailProperties.getUsername(), mailProperties.getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());

            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }


}

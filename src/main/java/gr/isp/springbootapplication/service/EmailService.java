package gr.isp.springbootapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachement(String emailSentTo, String subject, String emailBody, MultipartFile file) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(emailSentTo);

        helper.setSubject(subject);

        // default = text/plain
        // true = text/html
        helper.setText(emailBody, true);

        helper.addAttachment("CV.pdf", file);

        javaMailSender.send(msg);

    }

    public void sendEmail(String emailSentTo, String subject, String emailBody) throws MessagingException, IOException{

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(emailSentTo);

        helper.setSubject(subject);

        // default = text/plain
        // true = text/html
        helper.setText(emailBody, true);

        javaMailSender.send(msg);
    }
}

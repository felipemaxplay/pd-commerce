package br.com.felipemaxplay.pdcommerce.pdEmailService.service;

import br.com.felipemaxplay.pdcommerce.pdEmailService.model.Email;
import br.com.felipemaxplay.pdcommerce.pdEmailService.model.EmailStatus;
import br.com.felipemaxplay.pdcommerce.pdEmailService.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailServiceInt {
    @Value("${email.from}")
    private String from;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(Email email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setSubject(email.getSubject());
            message.setContent(email.getContent(), "text/html");
            javaMailSender.send(message);
            email.setEmailStatus(EmailStatus.SENT);
        } catch (MessagingException e) {
            email.setEmailStatus(EmailStatus.ERROR);
            logger.error("Erro aqui:" + e.getMessage());
        } finally {
            emailRepository.save(email);
        }
    }
}

package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    @Autowired
    private MailCreatorService mailCreatorService;

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail){
        log.info("Starting mail preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e){
            log.error("Failed to process mail sending: " + e.getMessage(), e);
        }
    }


    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mail.getToCc().ifPresent(cc -> mailMessage.setCc(cc.toString()));
        return mailMessage;
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTaskQuantityEmail(mail.getMessage()), true);
        };
    }


}

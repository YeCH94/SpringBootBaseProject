package com.chj.bootbase.service;

import com.chj.bootbase.domain.Mail;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void sendEmail(Mail mail) throws MessagingException, IOException, TemplateException;
}

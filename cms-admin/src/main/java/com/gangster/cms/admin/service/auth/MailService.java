package com.gangster.cms.admin.service.auth;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private FreeMarkerConfig config;

    public Boolean sendMail(String destination, String form, JavaMailSender mailSender) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Map<String,String> model=new HashMap<>();
            model.put("userEmail",destination);
            model.put("updatePasswordUrl","http://localhost:8080/sendMail/updatePassword/"+destination);
            Template template=config.getConfiguration().getTemplate("sendVerifyMail.ftl");
            String text=FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            helper.setFrom(form);
            helper.setTo(destination);
            helper.setSubject("Ganster-master       更改密码");
            helper.setText(text, true);
            mailSender.send(message);
            logger.info("邮件发送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("发送邮件时发生异常！！！");
            return false;
        } catch (IOException |TemplateException e) {
            e.printStackTrace();
            logger.error("邮件模板未找到！！！");
            return  false;
        }
    }
}

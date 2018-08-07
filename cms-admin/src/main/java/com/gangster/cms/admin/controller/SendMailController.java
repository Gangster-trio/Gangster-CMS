package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.service.auth.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sendMail")
public class SendMailController {
    public static final Logger logger = LoggerFactory.getLogger(SendMailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailService mailService;

    @Value("${mailFromMailAddr}")
    private String from;

    @PostMapping
    public void sendMail(@RequestBody String destintion) {
        mailService.sendMail( "hahaha", from, javaMailSender);
    }

    @GetMapping("/updatePassword/{userEmail}")
    public String updatePassword(@RequestParam("userEmail") String userEmail){
        return  userEmail;
    }
}

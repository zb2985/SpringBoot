package kopo.jjh.prj.dto;

import kopo.jjh.prj.security.dto.MailDto;
import kopo.jjh.prj.security.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class mailSend {
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    EmailService SendEmailService;

    public mailSend(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void mailSend(MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}

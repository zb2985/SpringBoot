package kopo.jjh.prj.mapper.impl;

import kopo.jjh.prj.util.CmmUtil;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service("MailService")
public class MailService implements IMailService {



    final String host = "smtp.gmail.com"; // 네이버에서 제공하는 SMTP서버
    final String user = "zb2985@gmail.com"; //네이버 이메일주소입력
    final String password ="wlgns@213"; // 비밀번호입력


    @Override
    public int doSendMail(MailDTO pDTO) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)

        // 메일 발송 성공여부(발송성공 : 1 / 발송실패 : 0)
        int res = 1;

        // 전달 받은 DTO로부터 데이터 가져오기(DTO객체가 메모리에 올라가지 않아 Null이 발생할 수 있기 떄문에 에러방지차원으로 if문 사용함)
        if (pDTO == null) {
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail());

        Properties props = new Properties();
        props.put("mail.smtp.host", host); // javax 외부 라이브러리에 메일 보내는 사람의 정보 설정
        props.put("mail.smtp.auth", "true"); // javax 외부 라이브러리에 메일 보내는 사람 인증 여부 설정
        props.put("mail.smtp.starttls.enable", "true"); // 로그인시 TLS를 사용
        props.put("mail.smtp.port", "587");     // TLS 포트트
        // 네이버 SMTP서버 인증 처리 로직
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            // 메일 제목
            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

            // 메일 내용
            message.setText(CmmUtil.nvl(pDTO.getContents()));

            // 메일발송
            Transport.send(message);

        } catch (MessagingException e) { // 메일 전송 관련 에러 다 잡기
            res = 0; // 메일 발송이 실패했기 때문에 0으로 변경


        } catch (Exception e) { // 모든 에러 다 잡기
            res = 0; // 메일 발송이 실패하기 떄문에 0으로 변경

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)

        return res;

    }

}

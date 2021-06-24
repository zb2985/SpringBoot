package kopo.jjh.prj.security.service;

import kopo.jjh.prj.domain.repository.AccountRepository;
import kopo.jjh.prj.mapper.impl.DAO;
import kopo.jjh.prj.security.dto.MailDto;
import kopo.jjh.prj.util.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
    public static final String FROM_ADDRESS = "본인의 이메일 주소를 입력하세요!";
    private final JavaMailSender emailSender;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String ePw = createKey();
private DAO mdao;

    private MimeMessage createMessage(String to)throws Exception{
        logger.info("보내는 대상 : "+ to);
        logger.info("인증 번호 : " + ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        String code = createCode(ePw);
        message.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        message.setSubject("확인 코드: " + code); //제목

        String msg="";
        msg += "<img width=\"120\" height=\"36\" style=\"margin-top: 0; margin-right: 0; margin-bottom: 32px; margin-left: 0px; padding-right: 30px; padding-left: 30px;\"  alt=\"\" loading=\"lazy\">";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를  가입 창이 있는 브라우저 창에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";
        msg += "<a href=\"http://\" style=\"text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">Slack Clone Technologies, Inc</a>";

        message.setText(msg, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("보내는 계정","slack-clone")); //보내는 사람

        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public void sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String createCode(String ePw){
        return ePw.substring(0, 3) + "-" + ePw.substring(3, 6);
    }
    public MailDto createMailAndChangePassword(String email, String name){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(name+"님의  임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요.  임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str,email);
        log.info("dto="+dto);
        return dto;
    }

    public void updatePassword(String str,String email){
        String password = EncryptionUtils.encryptMD5(str);
        log.info("update");
        String username = AccountRepository.findUserByUsername(email).getUsername();
        AccountRepository.updateUserPassword((username),password);
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }


    public void mailSend(MailDto dto) {
    }



}
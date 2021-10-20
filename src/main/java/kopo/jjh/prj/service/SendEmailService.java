package kopo.jjh.prj.service;

import kopo.jjh.prj.domain.repository.AccountRepository;
import kopo.jjh.prj.security.dto.MailDto;
import kopo.jjh.prj.util.EncryptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailService {
MailDto mailDto;
    @Autowired
    AccountRepository userRepository;

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "zombie2985@gmail.com";


    public MailDto createMailAndChangePassword(String email, String name) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(name + "님의 HOTTHINK 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" + "님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str, email);
        return dto;
    }

    public void updatePassword(String str, String email) {
        String password = EncryptionUtils.encryptMD5(str);
        String username = userRepository.findByUsername(email).getUsername();
        AccountRepository.updateUserPassword(username, password);
    }


    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto dto) {
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}

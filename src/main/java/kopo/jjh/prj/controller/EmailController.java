package kopo.jjh.prj.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kopo.jjh.prj.mapper.IUserService;
import kopo.jjh.prj.security.service.AccountService;
import kopo.jjh.prj.security.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@Controller
@Slf4j
@MapperScan(basePackages = "kopo/jjh/prj/mapper/impl/")
public class EmailController {
    @Autowired
    JavaMailSender mailSender;


    private final EmailService emailService;

    @RequestMapping(value = "mails", method = RequestMethod.GET)
    public void sendMailTest2() throws Exception {

        String subject = "test 메일";
        String content = "메일 테스트 내용.";
        String from = "보내는이 아이디@도메인주소";
        String to = "받는이 아이디@도메인주소";


        final MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                mailHelper.setFrom(from);
                mailHelper.setTo(to);
                mailHelper.setSubject(subject);
                mailHelper.setText(content, true);

            }

        };

        try {
            mailSender.send((SimpleMailMessage) preparator);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* 이메일 인증 */
    @RequestMapping(value = "mailCheck", method = RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception {

        /* 뷰(View)로부터 넘어온 데이터 확인 */
        log.info("이메일 데이터 전송 확인");
        log.info("인증번호 : " + email);

        Random random = new Random();
        int checkNum = random.nextInt(888888) + 1111111;
        log.info("인증번호" + checkNum);
        /* 이메일 보내기 */
        String setFrom = "zb2985@gmail.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + checkNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String num = Integer.toString(checkNum);
        return num;

    }

    /* 이메일 인증 */
    @RequestMapping(value = "mailCheck1", method = RequestMethod.GET)
    @ResponseBody
    public String GetPs(String email) throws Exception {

        /* 뷰(View)로부터 넘어온 데이터 확인 */

        log.info("email : " + email);


        String password = "1q2w3e4r5t";
        log.info("임시비밀번호" + password);
        /* 이메일 보내기 */
        String setFrom = "zb2985@gmail.com";
        String toMail = email;
        String title = "임시비밀번호.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "임시비밀번호는 " + password + "입니다." +
                        "<br>" +
                        "비밀번호를 입력하세요.";
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;

    }


    @PostMapping("verifyCode") // 이메일 인증 코드 검증
    public ResponseDto<?> verifyCode(@RequestBody Map<String, String> code) {
        if (EmailService.ePw.equals(code.get("code"))) {
            return new ResponseDto<>(true);
        } else {
            return new ResponseDto<>(false);
        }
    }

    @Autowired
    AccountService accountService;

    // 아이디 중복 검사
    @RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
    @ResponseBody
    public void memberIdChkPOST(String username) throws Exception {

        log.info("memberIdChk() 진입");

    } // memberIdChkPOST() 종료

    //아이디 중복확인
    @Autowired
    private IUserService userService;

    @GetMapping("idCheck")
    public @ResponseBody
    int idCheck(@RequestParam("username") String username) {
        int cnt = userService.idCheck(username);
        log.info("아이디체크");
        return cnt;
    }

    @GetMapping("emailCheck")
    public @ResponseBody
    int emailCheck(@RequestParam("email") String email) {
        int cnt1 = userService.emailCheck(email);
        log.info("아이디체크");
        return cnt1;
    }



}
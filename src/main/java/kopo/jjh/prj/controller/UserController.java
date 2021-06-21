package kopo.jjh.prj.controller;


import kopo.jjh.prj.mapper.UserService;
import kopo.jjh.prj.security.dto.AccountForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
@Slf4j
@Controller

public class UserController {

    @Autowired
    private UserService userService;





    // 아이디 찾기 페이지 이동
// 아이디 찾기 페이지 이동
    @RequestMapping(value="find_id_form")
    public String findIdView() {
        return "member/findId";
    }

    // 아이디 찾기 실행
    @RequestMapping(value="find_id", method=RequestMethod.POST)
    public String findIdAction(AccountForm vo, Model model) {
        log.info("find_id함수");
        AccountForm user = userService.findId(vo);

        if(user == null) {
            model.addAttribute("check", 1);
            log.info("user가 null아님"+1);
        } else {
            model.addAttribute("check", 0);
            model.addAttribute("id", user.getUsername());
        }

        return "member/findId";
    }

    // 비밀번호 찾기 페이지로 이동
    @RequestMapping(value="find_password_form")
    public String findPasswordView() {
        return "member/findPassword";
    }

    // 비밀번호 찾기 실행
    @RequestMapping(value="find_password", method= RequestMethod.POST)
    public String findPasswordAction(AccountForm vo, Model model) {
        AccountForm user = userService.findPassword(vo);

        if(user == null) {
            model.addAttribute("check", 1);
        } else {
            model.addAttribute("check", 0);
            model.addAttribute("updateid", user.getUsername());
        }

        return "member/findPassword";
    }

    // 비밀번호 바꾸기 실행
    @RequestMapping(value="update_password", method=RequestMethod.POST)
    public String updatePasswordAction(@RequestParam(value="updateid", defaultValue="", required=false) String username,
                                       AccountForm vo) {
        vo.setUsername(username);
        System.out.println(vo);
        userService.updatePassword(vo);
        return "member/findPasswordConfirm";
    }

    // 비밀번호 바꾸기할 경우 성공 페이지 이동
    @RequestMapping(value="check_password_view")
    public String checkPasswordForModify(HttpSession session, Model model) {
        AccountForm loginUser = (AccountForm) session.getAttribute("loginUser");

        if(loginUser == null) {
            return "/login";
        } else {
            return "/";
        }
    }
}
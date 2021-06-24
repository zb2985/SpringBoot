package kopo.jjh.prj.service.impl;

import kopo.jjh.prj.security.dto.AccountForm;

import javax.servlet.http.HttpServletResponse;

public interface MemberService {
    //이메일발송
    public void sendEmail(AccountForm vo, String div) throws Exception;

    //비밀번호찾기
    public void findPw(HttpServletResponse resp, AccountForm vo) throws Exception;
}

package kopo.jjh.prj.mapper;


import kopo.jjh.prj.security.dto.AccountForm;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    public int idCheck(String username);
    public int emailCheck(String email);

    AccountForm findId(AccountForm vo);

    AccountForm findPassword(AccountForm vo);

    void updatePassword(AccountForm vo);

    public String find_id(HttpServletResponse response, String email) throws Exception ;

}
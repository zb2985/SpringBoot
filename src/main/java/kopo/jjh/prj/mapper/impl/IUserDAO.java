package kopo.jjh.prj.mapper.impl;


import kopo.jjh.prj.security.dto.AccountForm;

public interface IUserDAO {
    public int idCheck(String username);//중복확인
    public int emailCheck(String email);//중복확인
    public AccountForm findId(AccountForm vo);
    public AccountForm findPassword(AccountForm vo);
    public void updatePassword(AccountForm vo);
    // 비밀번호 변경
    public int updatePw(AccountForm vo) throws Exception;
    public String find_id(String email) throws Exception;

}

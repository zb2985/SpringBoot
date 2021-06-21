package kopo.jjh.prj.mapper.impl;

import kopo.jjh.prj.mapper.UserVO;
import kopo.jjh.prj.security.dto.AccountForm;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    public AccountForm loginUser(AccountForm vo) {
        System.out.println("==> Mybatis로 loginUser() 기능 처리");
        return mybatis.selectOne("UserDAO.loginUser", vo);
    }

    public AccountForm findId(AccountForm vo) {
        System.out.println("==> Mybatis로 findId() 기능 처리");
        return mybatis.selectOne("UserDAO.findId", vo);
    }

    public AccountForm findPassword(AccountForm vo) {
        System.out.println("==> Mybatis로 findPassword() 기능 처리");
        return mybatis.selectOne("UserDAO.findPassword", vo);
    }

    public void updatePassword(AccountForm vo) {
        System.out.println("==> Mybatis로 updatePassword() 기능 처리");
        mybatis.update("UserDAO.updatePassword", vo);
    }
}
package kopo.jjh.prj.mapper.impl;

import kopo.jjh.prj.security.dto.AccountForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
@Slf4j
@Repository
public class UserDAOImpl implements IUserDAO {

    @Inject
    private SqlSession sqlSession;
    @Override
    public String find_id(String email) throws Exception{
        return sqlSession.selectOne("userMapper.find_id", email);
    }
    @Override//중복확인
    public int idCheck(String username) {
        log.info("username의 값 ="+username);
        int cnt=sqlSession.selectOne("userMapper.idCheck",username);
        return cnt;
    }
    @Override//중복확인
    public int emailCheck(String email) {
        log.info("username의 값 ="+email);
        int cnt1=sqlSession.selectOne("userMapper.emailCheck",email);
        return cnt1;
    }
    public AccountForm findId(AccountForm vo) {
        System.out.println("==> Mybatis로 findId() 기능 처리");
        return sqlSession.selectOne("UserDAO.findId", vo);
    }

    public AccountForm findPassword(AccountForm vo) {
        System.out.println("==> Mybatis로 findPassword() 기능 처리");
        return sqlSession.selectOne("UserDAO.findPassword", vo);
    }

    public void updatePassword(AccountForm vo) {
        System.out.println("==> Mybatis로 updatePassword() 기능 처리");
        sqlSession.update("UserDAO.updatePassword", vo);
    }
    @Override
    public int updatePw(AccountForm vo) throws Exception {
        return sqlSession.update("userMapper.updatePw", vo);
    }

}

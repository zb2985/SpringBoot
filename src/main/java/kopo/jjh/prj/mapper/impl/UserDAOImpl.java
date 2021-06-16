package kopo.jjh.prj.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
@Slf4j
@Repository
public class UserDAOImpl implements IUserDAO {

    @Inject
    private SqlSession sqlSession;

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

}

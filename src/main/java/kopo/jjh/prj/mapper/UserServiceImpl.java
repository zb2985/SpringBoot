package kopo.jjh.prj.mapper;

import kopo.jjh.prj.mapper.impl.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
  @Autowired
  UserDAOImpl userDAO;


    //아이디 중복확인
    @Override
    public int idCheck(String username) {
        int cnt=userDAO.idCheck(username);
        return cnt;
    }
    @Override
    public int emailCheck(String email) {
        int cnt1=userDAO.emailCheck(email);
        return cnt1;
    }
}
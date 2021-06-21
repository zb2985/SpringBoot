package kopo.jjh.prj.mapper.impl;


import kopo.jjh.prj.mapper.UserService;
import kopo.jjh.prj.security.dto.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("kopo.jjh.prj.mapper.UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public AccountForm findId(AccountForm vo) {
        return userDAO.findId(vo);
    }

    @Override
    public AccountForm findPassword(AccountForm vo) {
        return userDAO.findPassword(vo);
    }


    @Override
    public void updatePassword(AccountForm vo) {
        userDAO.updatePassword(vo);
    }
}
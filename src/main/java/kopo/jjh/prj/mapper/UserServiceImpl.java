package kopo.jjh.prj.mapper;

import kopo.jjh.prj.domain.repository.AccountRepository;
import kopo.jjh.prj.mapper.impl.UserDAOImpl;
import kopo.jjh.prj.security.domain.Account;
import kopo.jjh.prj.security.dto.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
    // 아이디 찾기
    @Override
    public String find_id(HttpServletResponse response, String email) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = userDAO.find_id(email);

        if (username == null) {
            out.println("<script>");
            out.println("alert('가입된 아이디가 없습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else {
            return username;
        }
    }
    public boolean userEmailCheck(String email, String name) {

        Account user = AccountRepository.findUserByUsername(email);
        if(user!=null && user.getName().equals(name)) {
            return true;
        }
        else {
            return false;
        }
    }
}
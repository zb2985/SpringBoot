package kopo.jjh.prj.mapper;


public interface IUserService {

    public int idCheck(String username);
    public int emailCheck(String email);

    boolean userEmailCheck(String email, String name);
}
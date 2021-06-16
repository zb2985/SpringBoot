package kopo.jjh.prj.mapper.impl;


public interface IUserDAO {
    public int idCheck(String username);//중복확인
    public int emailCheck(String email);//중복확인
}

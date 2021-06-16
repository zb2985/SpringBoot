package kopo.jjh.prj.mapper.impl;

import org.springframework.stereotype.Repository;

@Repository("kopo.jjh.prj.mapper.impl.DAO")
public interface DAO {
    int idCheck(String username);
    int emailCheck(String email);
}

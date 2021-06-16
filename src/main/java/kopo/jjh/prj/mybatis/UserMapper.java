package kopo.jjh.prj.mybatis;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository("userMapper") public class UserMapper extends AbstractMapper
    { public String getUserid(HashMap<String, Object> param)
        { return selectOne("mapper.UserMapper.getrank_chkeck_time", param); } }


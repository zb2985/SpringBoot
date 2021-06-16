package kopo.jjh.prj.mapper.impl;

import org.springframework.stereotype.Repository;

@Repository("kopo.jjh.prj.service.impl.Boardmapper")
public interface Boardmapper {
    public int userCheck(String username) throws Exception;


}

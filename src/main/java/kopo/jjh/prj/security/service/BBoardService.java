package kopo.jjh.prj.security.service;

import kopo.jjh.prj.mapper.impl.Boardmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class BBoardService {
    @Resource(name="kopo.jjh.prj.service.impl.Boardmapper")
    Boardmapper boardMapper;
    public int userCheck(String username) throws Exception {
        return boardMapper.userCheck(username);
    }


}

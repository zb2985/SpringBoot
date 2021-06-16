package kopo.jjh.prj.service.impl;

import kopo.jjh.prj.domain.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface Boardmapper{
    public List<Map<String, Object>> boardList(Criteria cri) throws Exception;

    public int boardListCnt() throws Exception;

}

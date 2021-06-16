package kopo.jjh.prj.service.impl;

import kopo.jjh.prj.domain.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface BoardInfomapper{
    public List<Map<String, Object>> boardInfoList(Criteria cri) throws Exception;

    public int boardInfoListCnt() throws Exception;

}

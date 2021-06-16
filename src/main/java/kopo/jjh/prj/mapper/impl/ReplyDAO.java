package kopo.jjh.prj.mapper.impl;

import kopo.jjh.prj.domain.entity.Criteria;
import kopo.jjh.prj.mapper.ReplyVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyDAO {
    void create(ReplyVO reply) throws Exception;
    void update(ReplyVO reply) throws Exception;
    void delete(Integer rno) throws Exception;
    List<ReplyVO> listPage(Long bno, Criteria cri) throws Exception;
    Long getToalCount(Long bno);
}
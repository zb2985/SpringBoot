package kopo.jjh.prj.mapper;

import kopo.jjh.prj.domain.entity.Criteria;

import java.util.List;

public interface ReplyService {
    void register(ReplyVO reply) throws Exception;
    void modify(ReplyVO reply) throws Exception;
    void remove(Integer rno) throws Exception;
    List<ReplyVO> listReplyPage(Long bno, Criteria cri) throws Exception;
    Long getTotalCount(Long bno) throws Exception;
}
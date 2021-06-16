package kopo.jjh.prj.mapper;

import kopo.jjh.prj.domain.entity.Criteria;
import kopo.jjh.prj.mapper.impl.ReplyDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Inject
    ReplyDAO replyDAO;

    @Override
    public void register(ReplyVO reply) throws Exception {
        replyDAO.create(reply);

    }
    @Override
    public void modify(ReplyVO reply) throws Exception {
        replyDAO.update(reply);

    }
    @Override
    public void remove(Integer rno) throws Exception {
        replyDAO.delete(rno);
    }
    @Override
    public List<ReplyVO> listReplyPage(Long bno, Criteria cri) throws Exception {
        return replyDAO.listPage(bno, cri);
    }
    @Override
    public Long getTotalCount(Long bno) throws Exception {
        return replyDAO.getToalCount(bno);
    }
}
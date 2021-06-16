package kopo.jjh.prj.mapper;

import kopo.jjh.prj.domain.entity.Criteria;
import kopo.jjh.prj.mapper.impl.ReplyDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Inject
   private SqlSession session;


    private static final String CREATE = "replyMapper.create";
    private static final String UPDATE = "replyMapper.update";
    private static final String DELETE = ".replyMapper.delete";
    private static final String LISTPAGE = "replyMapper.listPage";
    private static final String GETTOTALCOUNT = "replyMapper.getTotalCount";


    @Override
    public void create(ReplyVO reply) throws Exception {
        session.insert(CREATE, reply);

    }
    @Override
    public void update(ReplyVO reply) throws Exception {
        session.update(UPDATE, reply);

    }
    @Override
    public void delete(Integer rno) throws Exception {
        session.delete(DELETE, rno);

    }
    @Override
    public List<ReplyVO> listPage(Long bno, Criteria cri) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("bno", bno);
        paramMap.put("cri", cri);

        return session.selectList(LISTPAGE, paramMap);
    }
    @Override
    public Long getToalCount(Long bno) {
        return session.selectOne(GETTOTALCOUNT, bno);
    }

}
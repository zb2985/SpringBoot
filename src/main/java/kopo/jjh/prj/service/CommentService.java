package kopo.jjh.prj.service;


import kopo.jjh.prj.dto.CommentVO;
import kopo.jjh.prj.mapper.impl.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("kopo.jjh.prj.service.CommentService")
public class CommentService {

    @Resource(name="kopo.jjh.prj.mapper.impl.CommentMapper")
    CommentMapper mCommentMapper;

    public List<CommentVO> commentListService() throws Exception{

        return mCommentMapper.commentList();
    }

    public int commentInsertService(CommentVO comment) throws Exception{

        return mCommentMapper.commentInsert(comment);
    }

    public int commentUpdateService(CommentVO comment) throws Exception{

        return mCommentMapper.commentUpdate(comment);
    }
    public int recommendupdate(CommentVO comment) throws Exception{

        return mCommentMapper.recommendupdate(comment);
    }

    public int commentDeleteService(int cno) throws Exception{

        return mCommentMapper.commentDelete(cno);
    }

}




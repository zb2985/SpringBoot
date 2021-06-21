package kopo.jjh.prj.mapper.impl;


import kopo.jjh.prj.dto.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("kopo.jjh.prj.mapper.impl.CommentMapper")
public interface CommentMapper {
    // 댓글 개수
    public int commentCount() throws Exception;

    // 댓글 목록
    public List<CommentVO> commentList() throws Exception;

    // 댓글 작성
    public int commentInsert(CommentVO comment) throws Exception;

    // 댓글 수정
    public int commentUpdate(CommentVO comment) throws Exception;

    // 댓글 삭제
    public int commentDelete(int cno) throws Exception;

}




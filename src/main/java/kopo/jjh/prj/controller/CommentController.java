package kopo.jjh.prj.controller;


//5월31일 일단 중지

import kopo.jjh.prj.dto.CommentVO;
import kopo.jjh.prj.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource(name="kopo.jjh.prj.service.CommentService")
    CommentService mCommentService;


    @RequestMapping("/list") //댓글 리스트
    @ResponseBody
    private List<CommentVO> mCommentServiceList(Model model) throws Exception{
log.info("댓글 리스트");
        return mCommentService.commentListService();
    }

    @RequestMapping("/insert") //댓글 작성
    @ResponseBody
    private int mCommentServiceInsert(@RequestParam Long bno, @RequestParam String contents, @RequestParam int recommend ) throws Exception{
        log.info("댓글 작성");
        CommentVO comment = new CommentVO();
        comment.setBno(bno);
        comment.setContent(contents);
        comment.setRecommend(recommend);


        //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
        comment.setWriter("정지훈");

        return mCommentService.commentInsertService(comment);
    }

    @RequestMapping("/update") //댓글 수정
    @ResponseBody
    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String contents) throws Exception{
        log.info("댓글 수정");
        CommentVO comment = new CommentVO();
        comment.setCno(cno);
        comment.setContent(contents);

        return mCommentService.commentUpdateService(comment);
    }

    @PostMapping("/reco") //댓글추찬
    @ResponseBody
    private int UpdateProc(@RequestParam int cno , @RequestParam int recommend) throws Exception{

        CommentVO comment = new CommentVO();
        comment.setCno(cno);
        comment.setRecommend(recommend+1);


        log.info("추천수 = " + comment.recommend);

        return mCommentService.recommendupdate(comment);
    }


    @RequestMapping("/delete/{cno}") //댓글 삭제
    @ResponseBody
    private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
        log.info("댓글 삭제");
        return mCommentService.commentDeleteService(cno);
    }

}



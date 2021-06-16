package kopo.jjh.prj.controller;


//5월31일 일단 중지
/*
@Controller
@RequestMapping("/comment")
@Slf4j
public class CommentController {


    @Resource
    CommentService mCommentService;

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @RequestMapping("/cnolist") //댓글 리스트
    @ResponseBody
    private List<CommentVO> mCommentServiceList(Model model) throws Exception{
log.info("cnolist");
        return mCommentService.commentListService();

    }

    @RequestMapping("/insert") //댓글 작성
    @ResponseBody
    private int mCommentServiceInsert(@RequestParam Long bno, @RequestParam String content) throws Exception{

        CommentVO comment = new CommentVO();
        comment.setBno(bno);
        comment.setContent(content);
        //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
        comment.setWriter("test");
log.info("cnoinsert");
        return mCommentService.commentInsertService(comment);
    }

    @RequestMapping("/update") //댓글 수정
    @ResponseBody
    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{

        CommentVO comment = new CommentVO();
        comment.setCno(cno);
        comment.setContent(content);

        return mCommentService.commentUpdateService(comment);
    }

    @RequestMapping("/delete/{cno}") //댓글 삭제
    @ResponseBody
    private int mCommentServiceDelete(@PathVariable int cno) throws Exception{

        return mCommentService.commentDeleteService(cno);
    }

}


 */
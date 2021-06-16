package kopo.jjh.prj.api;

import kopo.jjh.prj.service.BoardService;
import kopo.jjh.prj.service.FileService;
import org.springframework.stereotype.Controller;

@Controller
public class ApiController {

    private BoardService boardService;
    private FileService fileService;
    public ApiController(FileService fileService ,BoardService boardService) {
        this.fileService = fileService;
        this.boardService = boardService;
    }
    //index


        /*
        @RequestMapping(value = "myRedis/test03")
        @ResponseBody
        public String test03(HttpServletRequest request, HttpServletResponse response) throws Exception {

            log.info(this.getClass().getName() + ".test03 start!");

            myRedisServcie.doSaveDataforListJSON();

            log.info(this.getClass().getName() + ".test03 end!");

            return "success";
        }
        */
}

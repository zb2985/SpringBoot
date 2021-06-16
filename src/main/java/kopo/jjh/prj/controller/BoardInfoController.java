package kopo.jjh.prj.controller;

import kopo.jjh.prj.dto.BoardInfoDto;
import kopo.jjh.prj.service.BoardInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class BoardInfoController {
   
    private static final Logger logger = LogManager.getLogger(BoardInfoController.class);

    private BoardInfoService boardInfoService;  //게시판
   
    
    public BoardInfoController(BoardInfoService boardInfoService) {

      
        this.boardInfoService = boardInfoService;
    
    }


/*
    @GetMapping("list")
    public String list(Model model) {
        List<BoardInfoDto> boardInfoDtoList = boardInfoService.getBoardList();
        model.addAttribute("postList", boardInfoDtoList);
        logger.info("글목록 실행");


        return "board/list.html";
    }

 */

  

    @GetMapping("Infolist")
    public String list(Long id, Model model,  @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardInfoDto> boardInfoList = boardInfoService.BoardInfolist(pageNum);
        Integer[] pageInfoList = boardInfoService.getPageInfoList(pageNum);
        model.addAttribute("boardInfoList",boardInfoList);
        model.addAttribute("pageInfoList", pageInfoList);


        return "boardinfo/list.html";
    }


    @GetMapping("Infopost")
    public String post() {
        log.info("글쓰기페이지");
        return "boardinfo/post.html";
    }

    @GetMapping("culture")
    public String culturepage() {
        log.info("문화관-일본어학습");
        return "culture/home2.html";
    }

   
    //파일업로드
    @PostMapping("Infopost")

    public String upload( BoardInfoDto boardInfoDto, int hitCnt) {

        log.info("write함수");
        boardInfoService.saveInfoPost(boardInfoDto);


        log.info("파일업로드");
        try {


            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */




            boardInfoService.saveInfoPost(boardInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/Infolist";
    }






    @GetMapping("Infopost/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardInfoDto boardInfoDto = boardInfoService.getInfoPost(id);
        model.addAttribute("Infopost", boardInfoDto);
        return "boardinfo/detail.html";
    }

    @GetMapping("Infopost/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardInfoDto boardInfoDto = boardInfoService.getInfoPost(id);
        model.addAttribute("Infopost", boardInfoDto);
        return "boardinfo/edit.html";
    }


    @PutMapping("Infopost/edit/{id}")
    public String update(BoardInfoDto boardInfoDto) {
        boardInfoService.saveInfoPost(boardInfoDto);
        return "redirect:/Infolist";
    }

    @DeleteMapping("Infopost/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardInfoService.deleteInfoPost(id);
        return "redirect:/Infolist";
    }





}


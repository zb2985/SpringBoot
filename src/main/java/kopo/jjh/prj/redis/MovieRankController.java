package kopo.jjh.prj.redis;

import kopo.jjh.prj.dto.MovieDTO;
import kopo.jjh.prj.service.IMovieRankService;
import kopo.jjh.prj.util.CmmUtil;
import kopo.jjh.prj.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
public class MovieRankController {
    private static final Logger logger = LogManager.getLogger(MovieRankController.class);
    @Resource(name = "MovieRankService")
    private IMovieRankService movieRankService;


            @RequestMapping(value = "rank/index")
    public String index(HttpServletRequest request, HttpServletResponse response)throws Exception{
        return "rank/index.html";
            }
            @RequestMapping(value="rank/movie")
    @ResponseBody
    public List<MovieDTO> rank(HttpServletRequest request, HttpServletResponse response) throws Exception{
                List<MovieDTO> rList=null;
                String send_msg = CmmUtil.nvl(request.getParameter("send_msg"));
                log.info("무비랭크서비스");
                if(((send_msg.indexOf("영화")>-1)||(send_msg.indexOf("영하")>-1)||(send_msg.indexOf("연하")>-1)||(send_msg.indexOf("영화")>-1)|| (send_msg.indexOf("연화")>-1))&& ((send_msg.indexOf("순위")>-1)||(send_msg.indexOf("순이")>-1))){
                    MovieDTO pDTO = new MovieDTO();
                    pDTO.setRank_chkeck_time(DateUtil.getDateTime("yyyyMMdd"));
                    rList = movieRankService.getMovieRank(pDTO);
                    if(rList==null){
                        rList = new ArrayList<MovieDTO>();
                    }
                }
    return rList;
            }

}

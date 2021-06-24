package kopo.jjh.prj.api;

import kopo.jjh.prj.api.air.domain.RResponse;
import kopo.jjh.prj.api.air.service.MainSService;
import kopo.jjh.prj.api.domain.Response;
import kopo.jjh.prj.api.service.MainService;
import kopo.jjh.prj.dto.papagodto;
import kopo.jjh.prj.service.demoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@EnableAutoConfiguration
@Slf4j
@RestController
public class AirRestController {
    @Autowired
 private demoService demoService;
    private MainService mainService;
    private MainSService mainSService;
    public AirRestController(MainService mainService ,MainSService mainSService){
        this.mainService = mainService;
        this.mainSService=mainSService;
    }
    @RequestMapping("chinese")
    @ResponseBody
    public String[] Chinese(@RequestParam(value = "korean", defaultValue = "-")String korean,
                            Model model) throws Exception{
        papagodto dd = new papagodto();
        dd.setKorean(korean);
        System.out.println(korean);
        String[] china = demoService.getChinese(dd).split("\"");
        model.addAttribute("chinese", china);

        log.info("dd="+dd);
        return china;
    }
    @PostMapping("/translate")
        protected String doPost (HttpServletRequest request, HttpServletResponse response) throws
                ServletException, IOException {
            System.out.println("NMTTestServlet doPost 메소드가 실행되었습니다.");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");

            //번역할 text 값을 받아 옵니다
            String original_str = (String) request.getParameter("original_str");

            //결과값 보내기 위한것
            PrintWriter out = response.getWriter();
            out.print((String) nmtReturnRseult(original_str));
    return "redirect:/translate2";
        }

// nmtReturnResult의 함수를 통해서 한글 - > 영어로 번역
    @GetMapping("/translate2")
        public String nmtReturnRseult (String original_str){

            //애플리케이션 클라이언트 아이디값";
            String clientId = "3_gqaAGqIO5b4lLHXhrD";
            //애플리케이션 클라이언트 시크릿값";
            String clientSecret = "DXqA0sX6q8";

            String resultString = "";
            try {
                //original_str 값이 우리가 변환할 값
                String text = URLEncoder.encode(original_str, "UTF-8");

                String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                String postParams = "source=ko&target=en&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else { // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());

                resultString = response.toString();
            } catch (Exception e) {
                System.out.println(e);
            }

            return "redirect:/culture";
        }



    @GetMapping("air")
    public RResponse getair(Model model){

        ResponseEntity<String> responseEntity = mainSService.getAAPi();
       RResponse response = mainSService.parser(responseEntity.getBody());
        model.addAttribute("air", response);
        log.info("air="+response);
        return response;
    }
    @ResponseBody
    @CrossOrigin
    @GetMapping("covid")
    public Response getCovid(Model model,AirRestController arc){

        ResponseEntity<String> responseEntity = mainService.getAPi();
        Response response = mainService.parser(responseEntity.getBody());
        model.addAttribute("covid", response);

        log.info("covid="+response);
        System.out.println(response);
        return response;
    }



}
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
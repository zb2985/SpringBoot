package kopo.jjh.prj.controller;

import kopo.jjh.prj.api.air.service.MainSService;
import kopo.jjh.prj.api.service.MainService;
import kopo.jjh.prj.dto.BoardDto;
import kopo.jjh.prj.dto.FileDto;
import kopo.jjh.prj.mapper.IUserService;
import kopo.jjh.prj.redis.MyRedisService;
import kopo.jjh.prj.security.domain.Account;
import kopo.jjh.prj.security.domain.SimpleUserDAO;
import kopo.jjh.prj.security.dto.AccountForm;
import kopo.jjh.prj.security.dto.UrlBuilder;
import kopo.jjh.prj.security.service.AccountService;
import kopo.jjh.prj.service.BoardService;
import kopo.jjh.prj.service.FileService;
import kopo.jjh.prj.util.MD5Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;

@Controller
@EnableAutoConfiguration
@Slf4j
public class BoardController {
    @Autowired
    SimpleUserDAO sud;
    // private IMovieService movieService;
    private static final Logger logger = LogManager.getLogger(BoardController.class);
    @Autowired
    private kopo.jjh.prj.service.demoService demoService;
    private MainService mainService;
    private MainSService mainSService;
    private final AccountService accountService;    //???????????? ??? ?????????
    private BoardService boardService;  //?????????
    private FileService fileService;    //???????????????
    //  private IMovieRankService movieRankService; //??????
    private MyRedisService myRedisServcie;
    private String CLIENT_ID = "3_gqaAGqIO5b4lLHXhrD"; //?????????????????? ??????????????? ????????????";
    private String CLI_SECRET = "DXqA0sX6q8"; //?????????????????? ??????????????? ????????????";
    private final String REDIRECT_URI = "http://localhost:8080/user/login/callback";
    private IUserService userService;

    public BoardController(IUserService userService,AccountService accountService, BoardService boardService, FileService fileService ,MyRedisService myRedisService) {
        this.userService =userService;
        this.accountService = accountService;
        this.boardService = boardService;
        this.fileService = fileService;
        this.myRedisServcie = myRedisServcie;
        //  this.movieService = movieService;
        //   this.movieRankService = movieRankService;
    }


/*
    @GetMapping("list")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        logger.info("????????? ??????");


        return "board/list.html";
    }

 */

    @RequestMapping(value="joinConfirm", method= RequestMethod.GET)
    public void emailConfirm(@ModelAttribute("form") AccountForm form, Model model) throws Exception {
        logger.info(form.getEmail() + ": auth confirmed");
        form.setAuthstatus(1);
        accountService.updateAuthStatus(form);
        model.addAttribute("auth_check", 1);

    }

    @GetMapping("list")
    public String list(Long id, Model model,  @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.Boardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        model.addAttribute("boardList",boardList);
        model.addAttribute("pageList", pageList);


        return "board/list.html";
    }


    @GetMapping("post")
    public String post() {
        log.info("??????????????????");
        return "board/post.html";
    }
    @GetMapping("rule")
    public String rule() {
        log.info("rule?????????");
        return "boardinfo/rule.html";
    }

    @GetMapping("passupdate")
    public String passupdate() {
        log.info("???????????????????????????");
        return "user/login/passupdate.html";
    }


    @RequestMapping("/naverlogin/{username}")
    public String loginWithoutForm(@PathVariable(value="username") String username) {
        log.info("???????????????");
        String roleStr = "ROLE_" + sud.getRolesByusername(username).toUpperCase();
        List<GrantedAuthority> roles = new ArrayList<>(1);
        //String roleStr = username.equals("admin") ? "ROLE_ADMIN" : "ROLE_GUEST";
        roles.add(new SimpleGrantedAuthority(roleStr));
        Account user = new Account(username, "", roles);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, roles);
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("????????????????????????");
        return "redirect:/";
    }
    private String getNaverOAuthURI(HttpSession session) throws UnsupportedEncodingException {
        String redirectURI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();

        UrlBuilder ub = new UrlBuilder("https://nid.naver.com/oauth2.0/authorize");
        ub
                .add("response_type", "code")
                .add("client_id", CLIENT_ID)
                .add("redirect_uri", redirectURI)
                .add("state", state);
        String apiURL = ub.toString();
        session.setAttribute("state", state);

        return apiURL;
    }


    @GetMapping("loginUser")
    public String createUserForm(Model model) {
        //AccountForm ac=new AccountForm();
        model.addAttribute("userForm", new AccountForm());

        //log.info("model:"+model.toString());
        log.info("?????????????????????");
        return "user/login/resister";

    }

    @RequestMapping("/idcheck.do")
    @ResponseBody
    public Map<Object, Object> idcheck(@RequestBody Long username_no) {

        Map<Object, Object> map = new HashMap<Object, Object>();

        AccountForm overlabCount = accountService.getoverlabCnt(username_no);
        map.put("overlabCount", overlabCount);

        return map;
    }
    private JavaMailSender javaMailSender;

    @RequestMapping("/CheckMail")
    public Map<String, Object> SendMail(String mail, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Random random = new Random();
        String key = "";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail); // ?????????????????? ?????? ????????? ?????? ????????? ????????? ??????
        // ?????? ?????? ?????? ??????
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(25) + 65; // A~Z?????? ?????? ????????? ??????
            key += (char) index;
        }
        int numIndex = random.nextInt(8999) + 1000; // 4?????? ????????? ??????
        key += numIndex;
        message.setSubject("???????????? ????????? ?????? ?????? ??????");
        message.setText("?????? ?????? : " + key);
        javaMailSender.send(message);
        log.info("message : " + message);
        map.put("key", key);
        log.info("key : " + key);
        log.info("??????????????????");

        return map;

    }
    //???????????????
    @PostMapping("post")

    public String upload(@RequestParam("file") MultipartFile files, BoardDto boardDto, int hitCnt) {

        log.info("write??????");
        boardService.savePost(boardDto);


        log.info("???????????????");
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* ???????????? ????????? 'files' ????????? ????????? ???????????????. */
            String savePath = System.getProperty("/user/dir") + "\\files";
            /* ????????? ???????????? ????????? ????????? ????????? ???????????????. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);

            Long fileId = fileService.saveFile(fileDto);
            boardDto.setFileId(fileId);
            boardService.savePost(boardDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/list";
    }
    @GetMapping("userlist")
    public String userlist(Long username_no, Model model, @PageableDefault(size = 10, sort = "username_no", direction = Sort.Direction.DESC) Pageable pageable) {


        model.addAttribute("userList", accountService.getuserList(pageable));


        return "user/userlist.html";
    }

    @GetMapping("user/{username_no}")
    public String userdetail(@PathVariable("username_no") Long username_no, Model model) {
        AccountForm accountForm = accountService.gethomeCnt(username_no);
        model.addAttribute("af", accountForm);
        return "admin/userdetail.html";
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("user/useredit/{username_no}")
    public String useredit(@PathVariable("username_no") Long username_no, Model model) {
        AccountForm accountForm = accountService.gethomeCnt(username_no);
        model.addAttribute("af", accountForm);
        return "admin/useredit.html";
    }


    @PutMapping("user/useredit/{username_no}")
    public String updateUser(@PathVariable Long username_no, AccountForm accountForm){

        accountService.updateUser(username_no, accountForm);
        return "redirect:/userlist";
    }


    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("user/{username_no}")
    public String delete(@PathVariable("username_no") long username_no) {
        accountService.delete(username_no);


        return "redirect:/userlist";
    }
   /* @DeleteMapping("user/{username_no}/passupdate")
    public String updatepassword(@PathVariable("password") String password ,AccountForm accountForm) {
        accountService.passdelete(password);
        accountService.updatePass(password ,accountForm);

        return "redirect:/";
    }

    */

    @GetMapping("post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    @GetMapping("post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }


    @PutMapping("post/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/list";
    }

    @DeleteMapping("post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/list";
    }


    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }






    public JSONObject exchange() {
        JSONObject result = new JSONObject();
        JSONArray arr = new JSONArray();

        // ????????? ???????????? ?????????
        String url = "https://finance.naver.com/marketindex/exchangeList.nhn";
        Document doc = null;

        try {
            // ???????????? ????????????
            doc = Jsoup.connect(url).get();
            // ?????????, ??????
            Elements country = doc.select(".tit");
            Elements sale = doc.select(".sale");

            for (int i = 0; i < 3; i++) {

                Element country_el = country.get(i);
                Element sale_el = sale.get(i);
                JSONObject obj = new JSONObject();

                obj.put("????????????", country_el.text());
                obj.put("??????", sale_el.text());
                arr.add(obj);

            }
            result.put("??????", arr);

        } catch (IOException e) {

            result.put("result", "????????? ??????????????????");
            e.printStackTrace();

        }

        return result;
    }


    @GetMapping("exchange")
    @ResponseBody
    @CrossOrigin
    public JSONObject sendExchange(BoardController c_service) throws Exception {

        return c_service.exchange();

    }




    public JSONObject news() {
        JSONObject result = new JSONObject();
        JSONArray arr = new JSONArray();

        // ?????????????????????
        String url = "https://learn.dict.naver.com/jpdic/today/conversation.nhn";
        Document doc = null;

        try {
            // ???????????? ????????????
            doc = Jsoup.connect(url).get();
            // ?????????, ??????
            Elements themess = doc.select(".kr_tit");//?????????
            Elements summaryy = doc.select(".jp_tit");       //??????



            Element theme = themess.get(0);
            Element summary = summaryy.get(0);
            JSONObject obj = new JSONObject();

            obj.put("theme", themess.text());
            obj.put("summary", summaryy.text());
            arr.add(obj);


            result.put("items", arr);     //items

        } catch (IOException e) {

            result.put("result", "????????? ??????????????????");
            e.printStackTrace();

        }

        return result;
    }


    @GetMapping("news")
    @ResponseBody
    @CrossOrigin
    public JSONObject sendnews(BoardController b_service) throws Exception {

        return b_service.news();

    }


    public JSONObject newss() {
        JSONObject result = new JSONObject();
        JSONArray arr = new JSONArray();

        // ?????????????????????
        String url = "https://www.hani.co.kr/arti/international/japan/home01.html";
        Document doc = null;

        try {
            // ???????????? ????????????
            doc = Jsoup.connect(url).get();
            // ?????????, ??????
            Elements themesss = doc.select(".article-title");//??????
            Elements summaryyy = doc.select(".article-prologue");       //??????

            for (int i = 0; i < 5; i++) {

                Element theme = themesss.get(i);
                Element summary = summaryyy.get(i);
                JSONObject obj = new JSONObject();

                obj.put("themee", themesss.text().split("???"));
                obj.put("summaryy", summaryyy.text().split("???"));
                arr.add(obj);

            }
            result.put("items", arr);     //items

        } catch (IOException e) {

            result.put("result", "????????? ??????????????????");
            e.printStackTrace();

        }

        return result;
    }


    @GetMapping("newss")
    @ResponseBody
    @CrossOrigin
    public JSONObject sendnewss(BoardController a_service) throws Exception {

        return a_service.newss();

    }



//redis ????????????



    @GetMapping("chatting")
    public String chat() {
        log.info("????????????????????????,??????????????????");
        return "culture/chatting.html";
    }
    /* ???????????? ?????? */
    @RequestMapping(value = "/member/findpw", method = RequestMethod.GET)
    public String findPwGET() throws Exception{
        return "member/findpw";
    }





    //???????????????

    @GetMapping("user")
    public String dispUser(Model model) {
        log.info("home controller");
        return "user/user";
    }
    // ????????? ?????? ???
    @GetMapping(value = "/find_id_form")
    public String find_id_form()  {
        return "member/find_id_form.html";
    }

    // ????????? ??????
    @PostMapping(value = "/find_id")
    public String find_id(HttpServletResponse response, @RequestParam("email") String email, Model md) throws Exception {
        log.info("response = "+response);
        md.addAttribute("username", userService.find_id(response, email));
        return "member/find_id.html";

    }

    @GetMapping("/login")
    public String login(){
        log.info("??????????????????");
        return "user/login/login";
    }
    @GetMapping("/book")
    public String oobks(){
        log.info("?????????");
        return "culture/book";
    }
    @GetMapping("/travel")
    public String travel(){
        log.info("???????????????");
        return "culture/travel";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }



    @PostMapping("loginUser")
    public String createUser(@Valid AccountForm form, BindingResult result) throws MessagingException, UnsupportedEncodingException {
        if (result.hasErrors()) {
            //????????????
            log.info("????????????");
            return "";
        }
        accountService.createUser(form);

        return "redirect:";
    }


    /**
     * ????????? ????????? ?????? ????????? ?????????
     *
     * @param session
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     * @throws UnknownHostException
     */

    @RequestMapping("/user/login/naverlogin")
    public String login(HttpSession session, Model model) throws UnsupportedEncodingException, UnknownHostException {
        String redirectURI = URLEncoder.encode("http://localhost:8080/user/login/callback", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
                CLIENT_ID, redirectURI, state);
        session.setAttribute("state", state);
        model.addAttribute("apiURL", apiURL);

        return "home/index.html";
    }

    /**
     * ?????? ????????? ????????????
     *
     * @param session
     * @param request
     * @param model
     * @return
     * @throws IOException
     * @throws ParseException

     @RequestMapping("/user/login/callback")
     public String naverCallback1(HttpSession session, HttpServletRequest request, Model model) throws IOException, ParseException {
     String code = request.getParameter("code");
     String state = request.getParameter("state");
     String redirectURI = URLEncoder.encode("http://localhost:8080/user/login/callback", "UTF-8");
     String apiURL;
     apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
     apiURL += "client_id=" + CLIENT_ID;
     apiURL += "&client_secret=" + CLI_SECRET;
     apiURL += "&redirect_uri=" + redirectURI;
     apiURL += "&code=" + code;
     apiURL += "&state=" + state;
     System.out.println("apiURL=" + apiURL);
     String res = requestToServer(apiURL);
     if (res != null && !res.equals("")) {
     model.addAttribute("res", res);
     Map<String, Object> parsedJson = new JSONParser(res).parseObject();
     System.out.println(parsedJson);
     session.setAttribute("currentUser", res);
     session.setAttribute("currentAT", parsedJson.get("access_token"));
     session.setAttribute("currentRT", parsedJson.get("refresh_token"));
     } else {
     model.addAttribute("res", "Login failed!");
     }
     return "/user/login/callback";
     }
     */

    @RequestMapping("/user/login/callback")
    public String naverCallback1(HttpSession session, HttpServletRequest request, Model model) throws IOException, ParseException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
        UrlBuilder ub = new UrlBuilder("https://nid.naver.com/oauth2.0/token");
        ub
                .add("grant_type", "authorization_code")
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLI_SECRET)
                .add("redirect_uri", redirectURI)
                .add("code", code)
                .add("state", state);
        System.out.println(ub);
        String apiURL = ub.toString();
        String res = requestToServer(apiURL);
        if(res != null && !res.equals("")) {
            Map<String, Object> parsedJson = new JSONParser(res).parseObject();
            if(parsedJson.get("access_token") != null) {

                //
                String infoStr = getProfileFromNaver(parsedJson.get("access_token").toString());
                Map<String, Object> infoMap = new JSONParser(infoStr).parseObject();
                log.info("access_token??????");
                if(infoMap.get("message").equals("success")) {
                    Map<String, Object> infoResp = (Map<String, Object>) infoMap.get("response");
                    String uniqueId = infoResp.get("id").toString();
                    System.out.println(uniqueId);
                    List<Map<String, String>> infoOAuth = sud.getOAuthInfoByProviderAndUniqueId("naver", uniqueId);
                    log.info("?????????id, provide??????");
                    if(infoOAuth.size() == 1) {
                        log.info("size==1");
                        System.out.println(infoOAuth);
                        // ????????? ????????? ?????? ????????? ?????? ????????? ?????? ????????? ??????
                        // - ?????? ???????????? ????????? ????????? ????????? ????????? ????????? ?????? ??????, ?????? ????????? ?????????????????? ??? ????????? ???????????? ????????????
                        loginWithoutForm(infoOAuth.get(0).get("username"));
                        log.info("??????????????????????????????");
                        model.addAttribute("isConnectedToNaver", true);
                    } else {
                        System.out.println("????????? ?????? ?????? ??????");
                        // ???????????? ?????? ????????? ?????? ???????????? ???????????? ????????? ????????? ?????? ????????? ??????
                        model.addAttribute("isConnectedToNaver", false);
                    }
                }
                session.setAttribute("currentUser", res);
                session.setAttribute("currentAT", parsedJson.get("access_token"));
                session.setAttribute("currentRT", parsedJson.get("refresh_token"));

                model.addAttribute("res", res);
            } else {
                model.addAttribute("res", "Login failed!");
            }
            System.out.println(parsedJson);
        } else {
            model.addAttribute("res", "Login failed!");
        }
        return "user/login/callback";
    }
    //????????? ??????
    public static void main(String[] args) {
        String token = "AAAAO-MO-JqqOgIj_tnFlddMFNe6NCZQzAzNjIF8Ib0FI0SoaHCv2pE5hmgSCaMDkI_ezYyoqYXtZVrfqhymxGAcFKg"; // ????????? ????????? ?????? ??????;
        String header = "Bearer " + token; // Bearer ????????? ?????? ??????


        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);


        System.out.println(responseBody);
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // ?????? ??????
                return readBody(con.getInputStream());
            } else { // ?????? ??????
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ?????? ??????", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL??? ?????????????????????. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("????????? ??????????????????. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ????????? ??????????????????.", e);
        }
    }



    /**
     * ?????? ?????? ?????? ????????? ????????????
     *
     * @param session
     * @param request
     * @param model
     * @param refreshToken
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping("/naver/refreshToken")
    public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken) throws IOException, ParseException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&refresh_token=" + refreshToken;
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL);
        model.addAttribute("res", res);
        session.invalidate();
        return "user/login/callback";
    }

    /**
     * ?????? ?????? ????????????
     *
     * @param session
     * @param request
     * @param model
     * @param accessToken
     * @return
     * @throws IOException
     */
    @RequestMapping("/naver/deleteToken")
    public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken) throws IOException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&access_token=" + accessToken;
        apiURL += "&service_provider=NAVER";
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL);
        model.addAttribute("res", res);
        session.invalidate();
        return "user/login/callback";
    }

    /**
     * ????????? ???????????? ??????????????? ????????? ??????
     *
     * @param accessToken
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/naver/getProfile")
    public String getProfileFromNaver(String accessToken) throws IOException {
        // ????????? ????????? ?????? ??????;
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String headerStr = "Bearer " + accessToken; // Bearer ????????? ?????? ??????
        String res = requestToServer(apiURL, headerStr);
        return res;
    }

    /**
     * ?????? ?????????(????????????)
     *
     * @param session
     * @return
     */
    @RequestMapping("/naver/invalidate")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * ?????? ?????? ?????????
     *
     * @param apiURL
     * @return
     * @throws IOException
     */
    private String requestToServer(String apiURL) throws IOException {
        return requestToServer(apiURL, "");
    }

    /**
     * ?????? ?????? ?????????
     *
     * @param apiURL
     * @param headerStr
     * @return
     * @throws IOException
     */
    private String requestToServer(String apiURL, String headerStr) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("header Str: " + headerStr);
        if(headerStr != null && !headerStr.equals("") ) {
            con.setRequestProperty("Authorization", headerStr);
        }
        int responseCode = con.getResponseCode();
        BufferedReader br;
        System.out.println("responseCode="+responseCode);
        if(responseCode == 200) { // ?????? ??????
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // ?????? ??????
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer res = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        if(responseCode==200) {
            return res.toString();
        } else {
            return null;
        }
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/lists";

    }
    @GetMapping("/search")
    public String infosearch(@RequestParam(value = "keyword")String keyword, Model model){
        List<BoardDto> boardInfoDtoList1 = boardService.searchPosts1(keyword);
        model.addAttribute("boardList",boardInfoDtoList1);
        return "board/list.html";
    }


}
package kopo.jjh.prj.service;

import kopo.jjh.prj.dto.papagodto;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class demoServiceImpl implements demoService{
    //애플리케이션 클라이언트 아이디값";
    private static final Logger logger = LogManager.getLogger(demoServiceImpl.class);
    String clientId = "3_gqaAGqIO5b4lLHXhrD";
    //애플리케이션 클라이언트 시크릿값";
    String clientSecret = "DXqA0sX6q8";

    @Override
    public String test() {
        return null;
    }
    @Override
    public String getChinese(papagodto dd) {
        String korean = dd.getKorean();
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        String result = "";
        String line = "";
        try {
            text = URLEncoder.encode(korean, "UTF-8");
            String param = "source=ko&target=ja&text=" + text;
            log.info("param="+param);
            URL url = new URL(apiURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(param);
            osw.flush();

            int responseCode = con.getResponseCode();
            result += "responseCode  : " + responseCode;
            result += "\n";
            // 200코드가 아니면 오류인데 무엇이 오류 인지 디버깅
            if (responseCode != 200) {
                Map<String, List<String>> map = con.getRequestProperties();
                result += "Printing Response Header...\n";
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if (entry.getKey().equals("apikey")) {
                        result += "";
                        log.info("result1 = "+ result);
                    } else {
                        result += "Key : " + entry.getKey() + " ,Value : " + entry.getValue();
                        log.info("result2 = "+ result);
                    }
                }

                log.info("param"+param);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // 여긴 출력
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
            log.info("br="+br);
            br.close();

        } catch (UnsupportedEncodingException e) {

            log.info("실패1");throw new RuntimeException("인코딩 실패", e);

        } catch (IOException e) {
            result = e.getMessage();
            log.info("실패2");
        }
    log.info("result="+result);

        return result;
    }
    @Override
    public String getEnglish(papagodto dd) {
        String korean = dd.getKorean();
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        String result = "";
        String line = "";
        try {
            text = URLEncoder.encode(korean, "UTF-8");
            String param = "source=ko&target=en&text=" + text;
            log.info("param="+param);
            URL url = new URL(apiURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(param);
            osw.flush();

            int responseCode = con.getResponseCode();
            result += "responseCode  : " + responseCode;
            result += "\n";
            // 200코드가 아니면 오류인데 무엇이 오류 인지 디버깅
            if (responseCode != 200) {
                Map<String, List<String>> map = con.getRequestProperties();
                result += "Printing Response Header...\n";
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if (entry.getKey().equals("apikey")) {
                        result += "";
                        log.info("result1 = "+ result);
                    } else {
                        result += "Key : " + entry.getKey() + " ,Value : " + entry.getValue();
                        log.info("result2 = "+ result);
                    }
                }

                log.info("param"+param);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // 여긴 출력
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
            log.info("br="+br);
            br.close();

        } catch (UnsupportedEncodingException e) {

            log.info("실패1");throw new RuntimeException("인코딩 실패", e);

        } catch (IOException e) {
            result = e.getMessage();
            log.info("실패2");
        }
        log.info("result="+result);

        return result;
    }
}



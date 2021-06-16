package kopo.jjh.prj.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kopo.jjh.prj.api.domain.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class MainService {

    @Value("iwmlm2wLyo4%2FWdSG7%2F9OHWYAKMBZ1rn2pbkIs4%2BIVr5QUvSn8x8rTXFITSAkbpeCJqJrN%2BerV8kiH6%2FwNwaiSg%3D%3D")
    String OPENAPI_KEY;

    @Value("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=iwmlm2wLyo4%2FWdSG7%2F9OHWYAKMBZ1rn2pbkIs4%2BIVr5QUvSn8x8rTXFITSAkbpeCJqJrN%2BerV8kiH6%2FwNwaiSg%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20210611&endCreateDt=20210612")
    String OPENAPI_URL;

    public ResponseEntity<String> getAPi(){
        String url = OPENAPI_URL + "?serviceKey=" + OPENAPI_KEY;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);
        System.out.println("response0"+response);
        return response;
    }
/*
 public String allowBasic() {
            StringBuffer result = new StringBuffer();
            try {
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService1/getMdcinPrductList");
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=발급받은 서비스키");
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
                urlBuilder.append("&type=json");
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
    BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    } else {
        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    }
    String line;
                while ((line = rd.readLine()) != null) {
        result.append(line + "\n");
    }
                rd.close();
                conn.disconnect();
} catch (Exception e) {
        e.printStackTrace();
        }

        return result + "";
        }
        }
 */


    public Response parser(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        Response response = null;
        try {
            response = xmlMapper.readValue(xml, Response.class);
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("response1="+response);
        return response;
    }


}
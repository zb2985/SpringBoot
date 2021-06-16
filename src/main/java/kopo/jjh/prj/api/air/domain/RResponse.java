package kopo.jjh.prj.api.air.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
@Slf4j
@Data
public class RResponse {
    public Header header;
    public Body body;



    public ResponseEntity<String> get(String url) {
        url = "http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=iwmlm2wLyo4%2FWdSG7%2F9OHWYAKMBZ1rn2pbkIs4%2BIVr5QUvSn8x8rTXFITSAkbpeCJqJrN%2BerV8kiH6%2FwNwaiSg%3D%3D" +
                "&numOfRows=2" +
                "&pageNo=1" +
                "&depAirportId=NAARKJJ" +
                "&arrAirportId=NAARKPC" +
                "&depPlandTime=20210612" +
                "&airlineId=AAR";
        return getStringResponseEntity(url);
    }

    public static ResponseEntity<String> getStringResponseEntity(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);
        log.info("좀되라 씨발="+response);
        return response;
    }

    public RResponse parser(String xml) {
        ObjectMapper xmlMapper = new ObjectMapper();
        RResponse response = null;
        try {
            response = xmlMapper.readValue(xml, RResponse.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("좀되라"+response);
        return response;
    }
}
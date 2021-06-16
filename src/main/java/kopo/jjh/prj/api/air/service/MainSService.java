package kopo.jjh.prj.api.air.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kopo.jjh.prj.api.air.domain.RResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class MainSService {

    @Value("iwmlm2wLyo4%2FWdSG7%2F9OHWYAKMBZ1rn2pbkIs4%2BIVr5QUvSn8x8rTXFITSAkbpeCJqJrN%2BerV8kiH6%2FwNwaiSg%3D%3D")
    String OPENAPI_KEY;

    @Value("http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=iwmlm2wLyo4%2FWdSG7%2F9OHWYAKMBZ1rn2pbkIs4%2BIVr5QUvSn8x8rTXFITSAkbpeCJqJrN%2BerV8kiH6%2FwNwaiSg%3D%3D" +
            "&numOfRows=2" +
            "&pageNo=1" +
            "&depAirportId=NAARKJJ" +
            "&arrAirportId=NAARKPC" +
            "&depPlandTime=20210612" +
            "&airlineId=AAR")
    String OPENAPI_URL;

    public ResponseEntity<String> getAAPi(){
        String url = OPENAPI_URL + "?serviceKey=" + OPENAPI_KEY;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);
        System.out.println("항공api"+response);
        return response;
    }


    public RResponse parser(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        RResponse response = null;
        try {
            response = xmlMapper.readValue(xml, RResponse.class);
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("항공정보왜안뜸?="+response);
        return response;
    }


}
package kopo.jjh.prj.api.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class Response {
    public Header Header;
    public Body body;


    public ResponseEntity<String> getStringResponseEntity(String url) {
        url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=iwmlm2wLyo4%2FWdSG7%2F9OHWYAKMBZ1rn2pbkIs4%2BIVr5QUvSn8x8rTXFITSAkbpeCJqJrN%2BerV8kiH6%2FwNwaiSg%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";
        return getStringResponseEntity(url);
    }
    public Response parser(String xml) {
        ObjectMapper xmlMapper = new ObjectMapper();
        Response response = null;
        try {
            response = xmlMapper.readValue(xml, Response.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
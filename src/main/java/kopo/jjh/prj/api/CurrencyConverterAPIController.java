package kopo.jjh.prj.api;


import kopo.jjh.prj.api.domain.ConvertInfoDto;
import kopo.jjh.prj.api.service.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyConverterAPIController {
    private final CurrencyConverterService currencyConverter;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    //국가에 따른 환율을 가져오는 메소드
    @GetMapping("/exchange-rates")
    public ResponseEntity getExchangeRate(@RequestParam(name = "receiveCountry") String receiveCountry){
        Double exchangeRate = currencyConverter.getCurrencyRate(receiveCountry);
        return new ResponseEntity(format(exchangeRate), HttpStatus.OK);
    }

    //송금액을 가져오는 메소드
    @PostMapping("/exchange-rates")
    public ResponseEntity getSendAmount(@Valid  @RequestBody ConvertInfoDto convertInfo){

        Double currency = currencyConverter.getCurrencyRate(convertInfo.getReceiveCountry());
        Double sendAmount = (currency * convertInfo.getSendAmount());
        String formatSendAmount = format(sendAmount);

        return new ResponseEntity(formatSendAmount, HttpStatus.OK);
    }

    public String format(Number number){
        return decimalFormat.format(number);
    }
}
package kopo.jjh.prj.api.service;

import kopo.jjh.prj.api.domain.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
    @Value("${currencyLayer.source}")
    private String sendCountry;

    private final CurrencyAPIService currencyAPIService;


    @Override
    public Double getCurrencyRate(String receiveCountry) {
        CurrencyDto currency = currencyAPIService.getCurrency();
        String sendReceiveCountry = sendCountry + receiveCountry;

        Double convertedCurrency = currency.getQuotes().get(sendReceiveCountry);

        return convertedCurrency;
    }
}
package com.shamy1st.currencyconversionservice.rest;

import com.shamy1st.currencyconversionservice.bean.CurrencyConversion;
import com.shamy1st.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeServiceProxy serviceProxy;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-conversion/{from}/{to}/{quantity}")
    public CurrencyConversion getExchangeValue(@PathVariable String from,
                                               @PathVariable String to,
                                               @PathVariable float quantity) {
        Map<String, String> params = new HashMap<>();
        params.put("from", from);
        params.put("to", to);
        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity(
        "http://localhost:8000/exchange-currency/{from}/{to}", CurrencyConversion.class, params);

        CurrencyConversion currencyConversion = response.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/{from}/{to}/{quantity}")
    public CurrencyConversion getExchangeValueFeign(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable float quantity) {
        CurrencyConversion currencyConversion = serviceProxy.getExchangeValue(from, to);
        currencyConversion.setQuantity(quantity);

        logger.info("{}", currencyConversion);

//        currencyConversion.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return currencyConversion;
    }
}

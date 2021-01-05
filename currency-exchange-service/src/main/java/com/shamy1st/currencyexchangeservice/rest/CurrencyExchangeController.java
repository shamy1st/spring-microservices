package com.shamy1st.currencyexchangeservice.rest;

import com.shamy1st.currencyexchangeservice.entity.ExchangeValue;
import com.shamy1st.currencyexchangeservice.service.ExchangeValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueService service;

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/exchange-currency/{from}/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = service.getExchangeValue(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        logger.info("{}", exchangeValue);

        return exchangeValue;
    }
}
package com.shamy1st.currencyconversionservice.proxy;

import com.shamy1st.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service")
@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    //@GetMapping("/exchange-currency/{from}/{to}")
    @GetMapping("/currency-exchange-service/exchange-currency/{from}/{to}")
    CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to);
}
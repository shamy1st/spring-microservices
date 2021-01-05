package com.shamy1st.limitsservice.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shamy1st.limitsservice.bean.LimitConfiguration;
import com.shamy1st.limitsservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration getLimitConfig() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackLimitConfigExample")
    public LimitConfiguration getLimitConfigExample() {
        throw new RuntimeException("Not Available!");
    }

    public LimitConfiguration fallbackLimitConfigExample() {
        return new LimitConfiguration(9, 999);
    }
}
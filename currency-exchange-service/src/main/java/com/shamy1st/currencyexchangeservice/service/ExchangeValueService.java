package com.shamy1st.currencyexchangeservice.service;

import com.shamy1st.currencyexchangeservice.entity.ExchangeValue;
import com.shamy1st.currencyexchangeservice.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeValueService {
    @Autowired
    private ExchangeValueRepository repository;

    public ExchangeValue getExchangeValue(String from, String to) {
        return repository.findByFromAndTo(from, to);
    }
}
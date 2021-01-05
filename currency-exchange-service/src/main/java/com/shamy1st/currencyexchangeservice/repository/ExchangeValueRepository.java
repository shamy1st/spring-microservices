package com.shamy1st.currencyexchangeservice.repository;

import com.shamy1st.currencyexchangeservice.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
    ExchangeValue findByFromAndTo(String from, String to);
}
package com.shop.bean.currency;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyConfig {
	@Bean
	public CurrencyRepository currencyRepository(DataSource dataSource) {
		return new CurrencyRepository(dataSource);
	}
}

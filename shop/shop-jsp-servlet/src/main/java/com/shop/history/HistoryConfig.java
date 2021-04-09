package com.shop.history;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.bean.orders.OrdersRepository;

@Configuration
public class HistoryConfig {
	@Bean
	public HistoryService historyService(OrdersRepository ordersRepository) {
		HistoryService hs = new HistoryService();
		hs.setOrdersRepository(ordersRepository);
		return hs;
	}
}

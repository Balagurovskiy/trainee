package com.shop.history;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.orders.Order;
import com.shop.bean.orders.OrdersRepository;

public class HistoryService {
	
	private OrdersRepository ordersRepository;
	
	public void setOrdersRepository(OrdersRepository ordersRepository) {
		this.ordersRepository = ordersRepository;
	}
	
	@Transactional(readOnly = true)
	public List<Order> getCustomerHistory(int customerId){
		return ordersRepository.getCustomerHistory(customerId);
	}
}

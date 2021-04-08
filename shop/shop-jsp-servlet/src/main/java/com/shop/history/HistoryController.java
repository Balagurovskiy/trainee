package com.shop.history;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.bean.customer.Customer;

@Controller
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	
	@GetMapping("/history")
	protected String loadHistory(HttpServletRequest req) {
    	
		Customer customer = null;
		HttpSession session = req.getSession(false);
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	}
    	int customerId = Objects.nonNull(customer) ? customer.getId() : -1;
    	String customerName = Objects.nonNull(customer) ? customer.getName() : "-";
		req.setAttribute("history", historyService.getCustomerHistory(customerId));
    	
        req.setAttribute("page_header", "INTERNET SHOP " + customerName + "'s history");
        req.setAttribute("message", "List processed orders:");
        
		return "history";
	}
}

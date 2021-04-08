package com.shop.account;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.bean.currency.Currency;
import com.shop.bean.customer.Customer;

@Controller
public class AccountController {
	
	@Autowired
	private CustomerCollectService customerCollectService;
	
	@GetMapping("/account")
    protected String loadCustomer(HttpServletRequest req) {
		
		Customer customer = null;

		HttpSession session = req.getSession();
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	} 
    	if (Objects.isNull(customer)) {
    		String id = req.getParameter("customer_cache");
    		customer = customerCollectService.getCustomerById(id);
    	}
    	
    	if (Objects.nonNull(customer)) {
        	req.setAttribute("customer", customer);
        	Currency curr = customer.getCash();
        	req.setAttribute("balance", curr.getAmount() + " " + curr.getName());
        	
            req.setAttribute("page_header", "INTERNET SHOP " + customer.getName() + "'s Account!");
            req.setAttribute("message", "Select from options below:");
            session.setAttribute("customer", customer);
            return "/account";
    	}
    	
		return "redirect:/welcome";
    }
}

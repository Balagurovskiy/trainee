package com.shop.account;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    		customer = customerCollectService.getCustomerById(req.getParameter("customer_cache"));
    	} else {
    		customer = customerCollectService.getAndRefreshCustomer(customer);
    	}
    	
    	if (Objects.nonNull(customer)) {
        	req.setAttribute("customer", customer);
        	req.setAttribute("balance", customer.getCash() + " " + customer.getCurrency().getName());
        	
            req.setAttribute("page_header", "INTERNET SHOP " + customer.getName() + "'s Account!");
            req.setAttribute("message", "Select from options below:");
            session.setAttribute("customer", customer);
            return "/account";
    	}
    	
		return "redirect:/welcome";
    }
}

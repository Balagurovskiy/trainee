package com.shop.register;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
	
	@Autowired
	private CustomerRegistrationService customerRegistrationService;
	
	@GetMapping("/register")
    protected String loadView(HttpServletRequest req, HttpServletResponse resp){
		req.getSession().invalidate();
        
		req.setAttribute("page_header", "New Customer in INTERNET SHOP!");
	    req.setAttribute("message", "Enter your name please:");
        
        return "/register";
    }
	
	@PostMapping("/register")
	protected String receiveName(HttpServletRequest req, HttpServletResponse resp) {

        String newName= req.getParameter("register_name");
        
        if (Objects.nonNull(newName) && !newName.isEmpty()) {
        	
        	HttpSession session = req.getSession(true);
        	
        	session.setAttribute("customer", customerRegistrationService.createAndReturn(newName));
        	session.setMaxInactiveInterval(5 * 60);
        	
			return "redirect:/account";
			
		} else {
			req.setAttribute("error", "Please dont leave that field blank");
			req.setAttribute("hasError", true);
			return loadView(req, resp);
		}
	}
}

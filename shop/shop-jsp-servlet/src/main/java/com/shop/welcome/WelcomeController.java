package com.shop.welcome;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {
	
	@Autowired
	private CustomerLoaderService customerLoaderService;
	
	@GetMapping({"/", "/welcome"})
	public String welcome(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
    	if (Objects.nonNull(session)) {
    		session.invalidate();
    	}
    	model.addAttribute("page_header", "Welcome to the INTERNET SHOP!");
		model.addAttribute("message", "Select registered customer or create new:");
              
		model.addAttribute("customers", customerLoaderService.getAll());
		
		return "/welcome";
	}
}
 

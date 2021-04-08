package com.shop.productlist;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.bean.customer.Customer;

@Controller
public class ProductListController {
	@Autowired
	private ProductListService productListService;
	@GetMapping("/product_list")
	protected String loadList(HttpServletRequest req) {
    	
    	req.setAttribute("products", productListService.getList());
    	
        req.setAttribute("page_header", "INTERNET SHOP Products");
        req.setAttribute("message", "List of available producs to order:");
        
		return "/product_list";
	}
	
	@PostMapping("/product_list")
	protected String putToBucket(HttpServletRequest req) {
		Customer customer = null;
		HttpSession session = req.getSession(false);
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	}
    	
    	String productId = req.getParameter("ordered_cache");
    	req.setAttribute("ordered", true);
    	productListService.putToTheBucket(customer.getId(), productId);
    	req.setAttribute("bucket_size", productListService.getBucketSize(customer.getId()));
    	
    	return loadList(req);
	}
}

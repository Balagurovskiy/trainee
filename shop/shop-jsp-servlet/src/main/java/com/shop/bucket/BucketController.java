package com.shop.bucket;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.bean.customer.Customer;
import com.shop.bucket.actions.BucketAction;
 
@Controller
public class BucketController {
	@Autowired
	private List<BucketAction> ACTIONS;
	
	private Customer customer;
	
	@Autowired
	private BucketLoaderService bucketLoaderService;
	
	private void loadCustomer(HttpServletRequest req) {
		HttpSession session = req.getSession();
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	}
	}
	@GetMapping("/bucket")
    protected String loadBucketList(HttpServletRequest req) {
		loadCustomer(req);
    	if (Objects.nonNull(customer)) {
    		return bucketLoaderService.sendBucketList(req, customer);
    	} return "/welcome";
    }

	@PostMapping("/bucket")
	protected String mangeBucketCommands(HttpServletRequest req){
		loadCustomer(req);
		Optional<String> redirect = ACTIONS.stream()
									.filter(a -> a.equalsTo((String)req.getParameter("action")))
									.map(a ->{
										a.setCustomer(customer);
										return a.execute(req);
									})
									.findAny();
		return ( redirect.isPresent() ) ? redirect.get() : "";
				
	}
}

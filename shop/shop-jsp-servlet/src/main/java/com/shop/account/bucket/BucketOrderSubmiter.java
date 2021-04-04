package com.shop.account.bucket;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BucketOrderSubmiter extends BucketAction {

	protected BucketOrderSubmiter() {
		super("buy");
	}

	@Override
	void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
    	if (Objects.nonNull(customer)) {
    		double total = new BucketTransactionManager().totalPrice( 
    										new BucketTransactionManager().getList(customer.getId()), 
    										customer.getCash() 
    										);
    		double sub = customer.getCash().getAmount() - total;
    		if (sub > 0.0) {
    			BucketTransactionManager bucketService = new BucketTransactionManager();
    			bucketService.submitOrder(customer.getId());
    			
    			bucketService.updateCustomerCash(customer.getId(), sub);
    			customer.getCash().setAmount(sub);
//    			req.getSession().setAttribute("customer", new CustomerCollectService().getCustomerById(customer.getId()));
    		}
    	}
		try {
			resp.sendRedirect(req.getContextPath() + "/account");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

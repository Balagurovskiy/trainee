package com.shop.bucket.actions;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.bucket.BucketTransactionManager;


public class BucketOrderSubmiter extends BucketAction {

	private BucketTransactionManager bucketTransactionManager;
	public void setBucketTransactionManager(BucketTransactionManager bucketTransactionManager) {
		this.bucketTransactionManager = bucketTransactionManager;
	}
	@Autowired
	public BucketOrderSubmiter(String type) {
		super(type);
	}

	@Override
	public String execute(HttpServletRequest req) {
		 
    	if (Objects.nonNull(customer)) {
    		double total = bucketTransactionManager.totalPrice( 
    										bucketTransactionManager.getList(customer.getId()), 
    										customer.getCash() 
    										);
    		double sub = customer.getCash().getAmount() - total;
    		if (sub > 0.0) {
    			bucketTransactionManager.submitOrder(customer.getId());
    			
    			bucketTransactionManager.updateCustomerCash(customer.getId(), sub);
    			customer.getCash().setAmount(sub);
    		}
    	}
		return "redirect:/account";
	}

}

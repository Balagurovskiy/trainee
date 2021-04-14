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
    		double total = bucketTransactionManager.totalPriceEntity( 
    										customer.getBucket(), 
    										customer.getCurrency().getKoef() 
    										);
    		double sub = customer.getCash() - total;
    		if (sub > 0.0) {
    			
    			bucketTransactionManager.processBucket(customer.getId());
    			
    			bucketTransactionManager.clearBucket(customer);
    			bucketTransactionManager.updateCustomerCash(customer, sub);
    		} 
    	}
		return "redirect:/account";
	}

}

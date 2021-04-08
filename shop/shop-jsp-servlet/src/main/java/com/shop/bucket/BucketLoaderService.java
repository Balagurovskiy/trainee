package com.shop.bucket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shop.bean.customer.Customer;
import com.shop.bean.orders.Order;

public class BucketLoaderService {
	private BucketTransactionManager bucketTransactionManager;
	public void setBucketTransactionManager(BucketTransactionManager bucketTransactionManager) {
		this.bucketTransactionManager = bucketTransactionManager;
	}
	public String sendBucketList(HttpServletRequest req, Customer customer) {
		req.setAttribute("page_header", "INTERNET SHOP " + customer.getName() + "'s bucket.");
	    req.setAttribute("message", "Order Submition. Available cash : " + customer.getCash().getAmount() + " " +customer.getCash().getName() );
	   
	    List<Order> products = bucketTransactionManager.getList(customer.getId());
	    req.setAttribute("bucket", products);
	    req.setAttribute("total", bucketTransactionManager.totalPrice( products, customer.getCash() ));
	    return "/bucket";
	}
}

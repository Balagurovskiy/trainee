package com.shop.bucket.actions;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.bucket.BucketCleanerManager;

public class BucketCleaner extends BucketAction {

	private BucketCleanerManager bucketCleanerManager;
	public void setBucketCleanerManager(BucketCleanerManager bucketCleanerManager) {
		this.bucketCleanerManager = bucketCleanerManager;
	}
	@Autowired
	public BucketCleaner(String type) {
		super(type);
	}

	@Override
	public String execute(HttpServletRequest req) {
    	if (Objects.nonNull(customer)) {
    		bucketCleanerManager.clean(customer);
    	}
		return "redirect:/account";
	}
}

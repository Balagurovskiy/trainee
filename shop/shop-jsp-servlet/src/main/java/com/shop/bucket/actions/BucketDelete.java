package com.shop.bucket.actions;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.bucket.BucketCleanerManager;
import com.shop.bucket.BucketLoaderService;

public class BucketDelete extends BucketAction {

	private BucketLoaderService bucketLoaderService;
	private BucketCleanerManager bucketCleanerManager;
	
	public void setBucketCleanerManager(BucketCleanerManager bucketCleanerManager) {
		this.bucketCleanerManager = bucketCleanerManager;
	}
	public void setBucketLoaderService(BucketLoaderService bucketLoaderService) {
		this.bucketLoaderService = bucketLoaderService;
	}
	@Autowired
	public BucketDelete(String type) {
		super(type);
	}

	@Override
	public String execute(HttpServletRequest req) {
    	if (Objects.nonNull(customer)) {
    		String oredrIdstr = req.getParameter("bucket_cache");
    		if (Objects.nonNull(oredrIdstr) && oredrIdstr.matches("\\d+")) {
    			bucketCleanerManager.cleanById(Integer.valueOf(oredrIdstr));
    		}
    	}
    	return bucketLoaderService.sendBucketList(req, customer);
	}

}

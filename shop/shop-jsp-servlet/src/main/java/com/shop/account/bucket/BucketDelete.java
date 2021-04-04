package com.shop.account.bucket;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BucketDelete extends BucketAction {

	protected BucketDelete() {
		super("delete");
	}

	@Override
	void execute(HttpServletRequest req, HttpServletResponse resp) {
    	if (Objects.nonNull(customer)) {
    		String oredrIdstr = req.getParameter("bucket_cache");
    		if (Objects.nonNull(oredrIdstr) && oredrIdstr.matches("\\d+")) {
    			System.out.println(oredrIdstr);
    			new BucketCleanerManager().cleanById(Integer.valueOf(oredrIdstr));
    		}
    	}
    	new BucketLoaderService().sendBucketList(req, resp, customer, requestDispatcher);
	}

}

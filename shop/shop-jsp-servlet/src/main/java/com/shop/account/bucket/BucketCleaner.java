package com.shop.account.bucket;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BucketCleaner extends BucketAction {

	protected BucketCleaner() {
		super("clean");
	}

	@Override
	void execute(HttpServletRequest req, HttpServletResponse resp) {
    	if (Objects.nonNull(customer)) {
    		new BucketCleanerManager().clean(customer.getId());
    	}
		try {
			resp.sendRedirect(req.getContextPath() + "/account");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

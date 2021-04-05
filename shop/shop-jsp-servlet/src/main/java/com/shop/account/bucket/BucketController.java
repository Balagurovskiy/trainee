package com.shop.account.bucket;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.bean.customer.Customer;

public class BucketController extends HttpServlet {

	private static final long serialVersionUID = 7903697819852434365L;
	private static final List<BucketAction> ACTIONS = Arrays.asList(
																	new BucketCleaner(),
																	new BucketOrderSubmiter(),
																	new BucketDelete()
																	);
	private Customer customer;
	private RequestDispatcher requestDispatcher;
	
	private void loadCustomer(HttpServletRequest req) {
		HttpSession session = req.getSession();
    	if (Objects.nonNull(session)) {
    		customer = (Customer) session.getAttribute("customer");
        	session.setMaxInactiveInterval(5 * 60);
    	}
	}
	private void setDispatcher(HttpServletRequest req) {
		String destination = "WEB-INF/view/bucket.jsp";
		requestDispatcher = req.getRequestDispatcher(destination);
	}
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setDispatcher(req);
		loadCustomer(req);
    	if (Objects.nonNull(customer)) {
    		new BucketLoaderService().sendBucketList(req, resp, customer, requestDispatcher);
    	} else {
    		resp.sendRedirect(req.getContextPath() + "/");
    	}
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loadCustomer(req);
		setDispatcher(req);
		ACTIONS.stream()
				.filter(a -> a.equalsTo((String)req.getParameter("action")))
				.forEach(a ->{
					a.setCustomer(customer);
					a.setRequestDispatcher(requestDispatcher);
					a.execute(req, resp);
				});
				
	}
}

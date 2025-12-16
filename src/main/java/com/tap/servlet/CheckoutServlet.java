package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import com.tap.Impl.OrderImpl;
import com.tap.Impl.OrderItemImpl;
import com.tap.pojo.Cart;
import com.tap.pojo.CartItem;
import com.tap.pojo.Order;
import com.tap.pojo.OrderItem;
import com.tap.pojo.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		
		PrintWriter out = response.getWriter();
		if(user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
		
		if(cart != null && user != null && !cart.getItems().isEmpty()) {
			String address = request.getParameter("address");
			String payment = request.getParameter("payment");
			System.out.println(payment);
			
			int restaurantId = (Integer)session.getAttribute("restaurantId");
			
			Order o = new Order();
			
			o.setUserId(user.getUserId());
			o.setRestaurantId(restaurantId);
			o.setOrderDate(new Timestamp(System.currentTimeMillis()));
			o.setTotalAmount((float)cart.getTotelPrice());
			o.setStatus("dispatched");
			o.setPaymentMode(payment);
			o.setAddress(address);
			
			OrderImpl oi = new OrderImpl();
			int orderId = oi.addOrder(o);
			System.out.println(orderId);
			
			for(CartItem ci:cart.getItems().values()) {
				int cartId = ci.getCartId();
				int quantity = ci.getQuantity();
				float totelPrice =(float) cart.getTotelPrice();
				
				OrderItem orderItem = new OrderItem(orderId, cartId, quantity, totelPrice);
				OrderItemImpl oit = new OrderItemImpl();
				oit.addOrderItem(orderItem);
			
			}
			RequestDispatcher rd = request.getRequestDispatcher("Success.jsp");
			rd.forward(request,response);
		}
	}

}

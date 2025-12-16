package com.tap.servlet;

import java.io.IOException;

import com.tap.Impl.MenuImpl;
import com.tap.pojo.Cart;
import com.tap.pojo.CartItem;
import com.tap.pojo.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart =(Cart) session.getAttribute("cart");
		
		 Integer newRestaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		 Integer existingId = (Integer)session.getAttribute("restaurantId");
		 if(existingId == null) {
			 existingId=0;
		 }
		if(cart == null || newRestaurantId!=existingId) {
			 cart = new Cart();
			 session.setAttribute("cart", cart);
			 session.setAttribute("restaurantId", newRestaurantId);
		}
		
		String action = request.getParameter("action");
		if(action.equals("add")) {
			addItemToCart(request,cart);
		}
		else if(action.equals("update")) {
			updateItemToCart(request,cart);
		}
		else if(action.equals("remove")) {
			removeItemToCart(request,cart);
		}
		response.sendRedirect("Cart.jsp");
	}

	private void addItemToCart(HttpServletRequest request, Cart cart) {

		int menuId = Integer.parseInt(request.getParameter("menuId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		MenuImpl mi = new MenuImpl();
		Menu oneMenu = mi.getOneMenu(menuId);
		
		if(oneMenu != null) {
			CartItem item = new CartItem(oneMenu.getMenuId(), oneMenu.getRestaurantId(), 
					oneMenu.getName(), quantity, oneMenu.getPrice());
			cart.addItem(item);
		}
	}

	private void updateItemToCart(HttpServletRequest request, Cart cart) {

		int menuId = Integer.parseInt(request.getParameter("menuId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		cart.updateItem(menuId, quantity);
	}

	private void removeItemToCart(HttpServletRequest request, Cart cart) {

		int menuId = Integer.parseInt(request.getParameter("menuId"));
		cart.removeItem(menuId);
	}
}

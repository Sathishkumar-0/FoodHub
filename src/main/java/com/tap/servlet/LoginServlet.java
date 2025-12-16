package com.tap.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.Impl.RestaurantImpl;
import com.tap.Impl.UserImpl;
import com.tap.pojo.Restaurant;
import com.tap.pojo.User;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserImpl ui = new UserImpl();
		User u = ui.getUser(email);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("user", u);
		System.out.println(u);
		
		if(email.equals(u.getEmail())&& password.equals(u.getPassword())) {
			RestaurantImpl ri = new RestaurantImpl();
			List<Restaurant> ar = ri.getAllRestaurant();
			request.setAttribute("ar",ar);
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.include(request, response);
		}
		else {
			out.print("Yout mail or password is incorrect");
		}
	}

}

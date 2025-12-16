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

import com.tap.Impl.UserImpl;
import com.tap.pojo.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String role = request.getParameter("role");
		
		UserImpl ui = new UserImpl();
		User user = ui.getUser(email);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(user == null) {
			User u = new User(name, userName, password, email, phone, address, role);
			ui.addUser(u);
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}else {
			RequestDispatcher req = request.getRequestDispatcher("mess.html");
			req.include(request, response);
//			out.println("user already registereds!");
		}
	}

}

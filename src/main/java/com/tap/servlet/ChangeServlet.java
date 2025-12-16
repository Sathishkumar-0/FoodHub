package com.tap.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.tap.Impl.UserImpl;
import com.tap.pojo.User;

@WebServlet("/changeservlet")
public class ChangeServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newpassword");
		String confirmPassword = request.getParameter("confirmpassword");
		PrintWriter out = response.getWriter();
		if(newPassword.equals(confirmPassword)) {
			UserImpl ui = new UserImpl();
			User u = ui.getUser(email);
			u.setPassword(confirmPassword);
			ui.updateUser(u);
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		else {
			out.print("confirm Password is not matching");
		}
	}

}

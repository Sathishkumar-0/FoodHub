package com.tap.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.Impl.MenuImpl;
import com.tap.pojo.Menu;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		
		MenuImpl mi = new MenuImpl();
		List<Menu> m = mi.getMenu(restaurantId);
		request.setAttribute("allmenu", m);
		RequestDispatcher rd = request.getRequestDispatcher("Menu.jsp");
		rd.forward(request, response);
	}

}

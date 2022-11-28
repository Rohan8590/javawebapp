package com.nagarro.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.ecommerce.service.LoginService;
import com.nagarro.ecommerce.service.LoginServiceImpl;

/**
 * Servlet to authenticate and login the user
 * 
 * @author rohanaggarwal
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4345790137325924345L;
	private LoginService loginService;

	/**
	 * Authenticates the user if the username and password match the database
	 * records
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		loginService = new LoginServiceImpl();

		String uname = request.getParameter("loginuname");
		String pass = request.getParameter("loginpass");

		HttpSession session = request.getSession();

		if (loginService.isValidUser(uname, pass)) {
			session.setAttribute("username", uname);
			session.setAttribute("isvalidunamepass", "true");
			response.sendRedirect("product_management.jsp");
		} else {
			session.setAttribute("isvalidunamepass", "false");
			response.sendRedirect("index.jsp");
		}
	}
}

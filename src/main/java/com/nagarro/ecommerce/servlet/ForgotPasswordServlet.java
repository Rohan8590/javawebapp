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
 * Servlet to retrieve the password of a user
 * 
 * @author rohanaggarwal
 *
 */
@WebServlet("/fpass")
public class ForgotPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 375678397076734489L;
	private LoginService loginService;

	/**
	 * Gets the password of a user if the username and email match the database
	 * records
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		loginService = new LoginServiceImpl();
		String uname = request.getParameter("loginuname");
		String email = request.getParameter("useremail");

		HttpSession session = request.getSession();

		if (loginService.isValidEmail(uname, email)) {
			String pass = loginService.getPassword(uname);
			session.setAttribute("isvalidunamemail", "true");
			session.setAttribute("userpass", pass);
			response.sendRedirect("fpass.jsp");
		} else {
			session.setAttribute("isvalidunamemail", "false");
			response.sendRedirect("fpass.jsp");
		}

	}
}

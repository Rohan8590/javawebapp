package com.nagarro.ecommerce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.ecommerce.model.ProductsInformation;
import com.nagarro.ecommerce.service.ProductsInformationService;
import com.nagarro.ecommerce.service.ProductsInformationServiceImpl;

/**
 * Sends all the products information entered by the user in the request
 * 
 * @author rohanaggarwal
 *
 */
public class GetProductsServlet extends HttpServlet {

	private static final long serialVersionUID = 7717993167404914760L;
	private ProductsInformationService productsInformationService;

	/**
	 * Sends all the products information entered by the user in the request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		productsInformationService = new ProductsInformationServiceImpl();

		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("username");

		List<ProductsInformation> userdata = productsInformationService.getList(uname);

		request.setAttribute("userdata", userdata);
		RequestDispatcher rd = request.getRequestDispatcher("product_management.jsp");
		rd.forward(request, response);

	}

}

package com.nagarro.ecommerce.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.nagarro.ecommerce.constants.EcommerceConstants;
import com.nagarro.ecommerce.service.ProductsInformationService;
import com.nagarro.ecommerce.service.ProductsInformationServiceImpl;

/**
 * The servlet that deleted the data of a particular row
 * 
 * @author rohanaggarwal
 *
 */
@WebServlet("/deletedata")
public class DeleteProductServlet extends HttpServlet {

	private static final long serialVersionUID = -3387488718256659356L;
	private ProductsInformationService productsInformationService;

	/**
	 * Deletes a row as well as the image file corresponding to it
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		productsInformationService = new ProductsInformationServiceImpl();

		String tempId = request.getQueryString();
		int id = Integer.parseInt(tempId.substring(tempId.lastIndexOf("=") + 1));

		// Deleting the image corresponding to the row
		String image = productsInformationService.get(id).getImage();
		FileUtils.touch(new File(EcommerceConstants.UPLOAD_DIRECTORY + File.separator + image));
		File filetodelete = FileUtils.getFile(EcommerceConstants.UPLOAD_DIRECTORY + File.separator + image);
		FileUtils.deleteQuietly(filetodelete);

		// Deleting the row
		productsInformationService.delete(id);
		response.sendRedirect("product_management.jsp");

	}

}

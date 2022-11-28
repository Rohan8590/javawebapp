package com.nagarro.ecommerce.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.nagarro.ecommerce.constants.EcommerceConstants;
import com.nagarro.ecommerce.model.ProductsInformation;
import com.nagarro.ecommerce.service.ProductsInformationService;
import com.nagarro.ecommerce.service.ProductsInformationServiceImpl;

/**
 * Servlet to insert data into the database
 * 
 * @author rohanaggarwal
 *
 */
@MultipartConfig(fileSizeThreshold = 1024*1024*1)
@WebServlet("/insertInDB")
public class AddProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1534374033579053226L;
	private ProductsInformationService productsInformaitonService;

	/**
	 * Inserts new row in the products information database
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		productsInformaitonService = new ProductsInformationServiceImpl();

		Part filePart = request.getPart("pimage");
		byte[] imagenew = new byte[filePart.getInputStream().available()];
		filePart.getInputStream().read(imagenew);
		
		String imageFileName = filePart.getSubmittedFileName();

		String uploadPath = EcommerceConstants.UPLOAD_DIRECTORY + imageFileName;
	
		String title;
		int quantity;
		int size;
		String image = null;
		long image_size = 0;

		title = (String) request.getAttribute("ptitle");
		quantity = Integer.parseInt((String) request.getAttribute("pquantity"));
		size = Integer.parseInt((String) request.getAttribute("psize"));
		
		
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("username");

		// Get updated data
		HashMap<String, String> formFieldData = new HashMap<String, String>();
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					//populate form field
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();
					formFieldData.put(fieldname, fieldvalue);
				} else {
					image = FilenameUtils.getName(item.getName());
					image_size = item.getSize();

					// Change name if file with same name already exists
					int n = 1;
					while (productsInformaitonService.fileExists(image)) {
						int dotIdx = image.lastIndexOf('.');
						if (n == 1) {
							image = image.substring(0, dotIdx) + n + image.substring(dotIdx);
						} else {
							image = image.substring(0, dotIdx - 1) + n + image.substring(dotIdx);
						}
						n++;
					}
					item.write(new File(EcommerceConstants.UPLOAD_DIRECTORY + File.separator + image));
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Store the updated data in temporary variables
		title = formFieldData.get("ptitle");
		quantity = Integer.parseInt(formFieldData.get("pquantity"));
		size = Integer.parseInt(formFieldData.get("psize"));

		// Don't add if image size exceeds the specified limit
		if (image_size > EcommerceConstants.IMAGE_SIZE_LIMIT) {
			session.setAttribute("exceedsimagesize", "true");
			response.sendRedirect("product_management.jsp");
			return;
		}

		/*
		 * Don't add if the user has already exceeded his total image size limit or will
		 * exceed if on adding the new image
		 */
		if (productsInformaitonService.exceedstotalimagesize(uname, image_size)) {
			session.setAttribute("exceedstotalimagesize", "true");
			response.sendRedirect("product_management.jsp");
			return;
		}

		ProductsInformation productsInformation = new ProductsInformation(uname, title, quantity, size, image,
				image_size);

		productsInformaitonService.insert(productsInformation);
		response.sendRedirect("product_management.jsp");

	}
}

package com.nagarro.ecommerce.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.nagarro.ecommerce.constants.EcommerceConstants;
import com.nagarro.ecommerce.model.ProductsInformation;
import com.nagarro.ecommerce.service.ProductsInformationService;
import com.nagarro.ecommerce.service.ProductsInformationServiceImpl;

/**
 * The servlet that updates the data of a particular row
 * 
 * @author rohanaggarwal
 *
 */
@WebServlet("/editData")
public class EditProductServlet extends HttpServlet {

	private static final long serialVersionUID = -9141776781514985536L;
	private ProductsInformationService productsInformationService;

	/**
	 * Updates the data of a particular row and also deletes the previous image
	 * corresponding to a row and adds a new one
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		productsInformationService = new ProductsInformationServiceImpl();

		String title;
		int quantity;
		int size;
		String image = null;
		long image_size = 0;

		HttpSession session = request.getSession();
		int id = Integer.parseInt((session.getAttribute("id").toString()));
		String uname = (String) session.getAttribute("username");

		// Delete old image
		String oldimage = productsInformationService.get(id).getImage();
		FileUtils.touch(new File(EcommerceConstants.UPLOAD_DIRECTORY + File.separator + oldimage));
		File filetodelete = FileUtils.getFile(EcommerceConstants.UPLOAD_DIRECTORY + File.separator + oldimage);
		FileUtils.deleteQuietly(filetodelete);

		// Get updated data
		HashMap<String, String> formFieldData = new HashMap<String, String>();
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();
					formFieldData.put(fieldname, fieldvalue);
				} else {
					image = FilenameUtils.getName(item.getName());
					image_size = item.getSize();

					// Change name if file with same name already exists
					int n = 1;
					while (productsInformationService.fileExists(image)) {
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

		// Don't update if image size exceeds the specified limit
		if (image_size > EcommerceConstants.IMAGE_SIZE_LIMIT) {
			session.setAttribute("exceedsimagesizeedit", "true");
			response.sendRedirect("edit.jsp?prodId=" + id);
			return;
		}

		/*
		 * Don't update if the user has already exceeded his total image size limit or
		 * will exceed if on adding the new image
		 */
		if (productsInformationService.exceedstotalimagesize(uname, image_size)) {
			session.setAttribute("exceedstotalimagesizeedit", "true");
			response.sendRedirect("edit.jsp?prodId=" + id);
			return;
		}

		ProductsInformation productsInformation = new ProductsInformation(uname, title, quantity, size, image,
				image_size);
		productsInformation.setId(id);

		productsInformationService.update(productsInformation);
		response.sendRedirect("product_management.jsp");
	}
}

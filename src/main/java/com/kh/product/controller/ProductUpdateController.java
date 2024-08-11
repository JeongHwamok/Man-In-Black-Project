package com.kh.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.image.model.vo.Image;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/update.pr")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
			
			String savePath = request.getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			// MultupartRequest 객체 생성
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
			int productNo = Integer.parseInt(multiRequest.getParameter("pno"));
			String productName = multiRequest.getParameter("productName");
			String productContent = multiRequest.getParameter("productContent");
			String subCategoryNo =  multiRequest.getParameter("subCategoryNo");
			int productPrice = Integer.parseInt(multiRequest.getParameter("productPrice"));
			int productStock = Integer.parseInt(multiRequest.getParameter("productStock"));
			String productSize = multiRequest.getParameter("productSize");
			
			Product p = new Product(); 
			
			p.setProductNo(productNo);
			p.setProductName(productName);
			p.setProductComment(productContent);
			p.setSubcategoryNo(subCategoryNo);
			p.setProductPrice(productPrice);
			p.setProductStock(productStock);
			p.setSizeNo(productSize);
			
			ArrayList<Image> newList = new ArrayList<>();
			//int result = new ProductService().updateProduct(p, newList);
			
			// System.out.println(multiRequest.getParameter("FileNo1")); 
			
			
			// 수정안한 파일은 file 파라미터로 넘어오지 않는 문제
			for(int i=1; i<=7; i++) {
				
				if(multiRequest.getOriginalFileName("file" + i) != null) {
					Image img = new Image();
					if(i == 1) {
						img.setImageLevel(1);
					} else {
						img.setImageLevel(2);
					}
					
					img.setOriginName(multiRequest.getOriginalFileName("file" + i));
					img.setChangeName(multiRequest.getFilesystemName("file" + i));
					
					String filePath = "resources/thumbnail_upfiles/";
					
					img.setImagePath(filePath);
					newList.add(img);
				} else if(!multiRequest.getParameter("FileName" + i).equals("")) {
					Image img = new Image();
					if(i == 1) {
						img.setImageLevel(1);
					} else {
						img.setImageLevel(2);
					}
					
					img.setOriginName(multiRequest.getParameter("FileOriginName" + i));
					img.setChangeName(multiRequest.getParameter("FileName" + i));
					
					String filePath = "resources/thumbnail_upfiles/";
					
					img.setImagePath(filePath);
					newList.add(img);
				}
				
				
			}
			
			int result = new ProductService().updateProduct(p, newList);
			
			if(result > 0 && newList.size() > 0) {
				for(int i = 1; i <= 7; i++) {
					if(!multiRequest.getParameter("FileName" + i).equals("")) {
						new File(savePath + multiRequest.getParameter("FileOriginName" + i)).delete();
						//System.out.println(multiRequest.getParameter("FileName" + i));
					}
				}
				
				request.setAttribute("alert", "상품 수정 성공");
				request.setAttribute("", response);
				response.sendRedirect("productList.ad");
				
			} else {
				
			}
	
		
		
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.category.model.service.CategoryService;
import com.kh.category.model.vo.Category;
import com.kh.common.MyFileRenamePolicy;
import com.kh.image.model.vo.Image;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.size.model.service.SizeService;
import com.kh.size.model.vo.Size;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/insert.pr")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	
		// multipart 형식 검사 진행
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 전송용량제한
			int maxSize = 10 * 1024 * 1024;
			
			// 저장폴더 경로
			String savePath = request.getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			// MultupartRequest 객체 생성
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String productName = multiRequest.getParameter("productName");
			String productContent = multiRequest.getParameter("productContent");
			// int categoryNo =  Integer.parseInt(multiRequest.getParameter("categoryNo"));
			String subCategoryNo =  multiRequest.getParameter("subCategoryNo");
			
			
			
			// 카테고리 대분류-소분류 네임 가져오기
			// Category c = new CategoryService().selectCategory(categoryNo);
			// Category sc = new CategoryService().selectSubCategory(subCategoryNo);
			
			
			
			int productPrice = Integer.parseInt(multiRequest.getParameter("productPrice"));
			int productStock = Integer.parseInt(multiRequest.getParameter("productStock"));
			String productSize = multiRequest.getParameter("productSize");
			
			Product p = new Product(); 
			p.setProductName(productName);
			p.setProductComment(productContent);
			p.setSubcategoryNo(subCategoryNo);
			p.setProductPrice(productPrice);
			p.setProductStock(productStock);
			p.setSizeNo(productSize);
			
			ArrayList<Image> ilist = new ArrayList<> ();
			
			// 파일 객체 받아오기
			for(int i=1; i<=7; i++) {
					
					String key = "file" + i;
					// > file1, file2, file3, file4... 
					
					// 해당 키값으로 넘어온 첨부파일이 있는지 먼저 검사
					if(multiRequest.getOriginalFileName(key) != null) {
						// 해당 키값에 딸린 첨부파일이 있을 경우
						
						// Attachment 객체 생성
						Image img = new Image();
						// > 원본명, 수정명, 폴더경로, 파일레벨
						
						String originName = multiRequest.getOriginalFileName(key);
						
						String changeName = multiRequest.getFilesystemName(key);
						
						String filePath = "resources/thumbnail_upfiles/";
						// > 위의 세개의 데이터는 대표이미지든 상세이미지든
						//   공통적으로 들어가야 하는 데이터임		
						
						img.setOriginName(originName);
						img.setChangeName(changeName);
						img.setImagePath(filePath);
						
						// 대표이미지냐, 상세이미지냐에 따라서 fileLevel 필드 세팅
						if(i == 1) { // 대표이미지일 경우
							
							img.setImageLevel(1);
							
						} else { // 상세이미지일 경우
							
							img.setImageLevel(2);
							
						}
						
						ilist.add(img);
					}
				}
			int result = new ProductService().insertProduct(p, ilist);
			
			ArrayList<Size> slist = new SizeService().selectSizeList();
			
			request.setAttribute("slist", slist);
			
			ArrayList<Product> list = new ProductService().adminSelectProductAll();
			
			request.setAttribute("list", list);
			
			ArrayList<Category> sCategory = new CategoryService().selectAllSubCategoryList();
			
			request.setAttribute("sCategoryList", sCategory);
			
			request.getRequestDispatcher("views/admin/product/productListView.jsp").forward(request, response);
			
			//request.getRequestDispatcher("views/admin/product/productListView.jsp").forward(request, response);
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

package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.category.model.service.CategoryService;
import com.kh.category.model.vo.Category;
import com.kh.image.model.vo.Image;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.size.model.service.SizeService;
import com.kh.size.model.vo.Size;

/**
 * Servlet implementation class ProductUpdateFormController
 */
@WebServlet("/updateForm.pr")
public class ProductUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		ArrayList<Product> prList = new ProductService().selectProductDetail(productNo);
		// 카테고리 대분류 목록
		ArrayList<Category> clist = new CategoryService().selectCategoryList();
		
		int categoryNo = 0;
		
		
		// 카테고리 대분류 반복문 돌려서 가져온 상품리스트의 카테고리명이 서로 일치할 경우
		// 카테고리 번호를 뽑아오는 반복문
		for(int i=0; i<clist.size(); i++) {
			
			if(clist.get(i).getCategoryName().equals(prList.get(0).getCategoryNo())) {
				
				categoryNo = clist.get(i).getCategoryNo();
				
			} 
			
		}
		
		
		
		// 카테고리 소분류 목록
		ArrayList<Category> subList = new CategoryService().selectSubCategoryList(categoryNo);
				
		// 사이즈 목록
		ArrayList<Size> sizeList = new SizeService().selectSizeList();
		
		// Image 테이블 가져오기
		ArrayList<Image> imgList = new ProductService().selectImgList(productNo);
		
		
		request.setAttribute("prList", prList);
		request.setAttribute("sublist", subList);
		request.setAttribute("clist", clist);
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("imgList", imgList);
		
		for(Image i : imgList) {
		}
		request.getRequestDispatcher("views/product/productUpdateView.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

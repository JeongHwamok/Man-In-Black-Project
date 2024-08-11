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
import com.kh.size.model.service.SizeService;
import com.kh.size.model.vo.Size;

/**
 * Servlet implementation class ProductEnrollController
 */
@WebServlet("/enroll.pr")
public class ProductEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		// 카테고리 대분류 목록
		ArrayList<Category> list = new CategoryService().selectCategoryList();
		
		// 카테고리 소분류 목록
		ArrayList<Category> subList = new CategoryService().selectSubCategoryList(1);
		
		// 사이즈 목록
		ArrayList<Size> sizeList = new SizeService().selectSizeList();
		
		// 이제 아무대서나 ArrayList<Category> 객체 생성해서 카테고리 list 조회할수 있음
//		for(Category c : list) {
//		
//			System.out.println(c);
//		}
		
		request.setAttribute("sublist", subList);
		request.setAttribute("list", list);
		request.setAttribute("sizeList", sizeList);
		
		request.getRequestDispatcher("views/product/productInsert.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

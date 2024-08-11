package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.category.model.service.CategoryService;
import com.kh.category.model.vo.Category;
import com.kh.member.model.vo.Member;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.size.model.service.SizeService;
import com.kh.size.model.vo.Size;

/**
 * Servlet implementation class productListController
 */
@WebServlet("/productList.ad")
public class AdminProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Product> list = new ProductService().adminSelectProductAll();
		
		request.setAttribute("list", list);
		
		ArrayList<Size> slist = new SizeService().selectSizeList();
		
		request.setAttribute("slist", slist);
		
		ArrayList<Category> sCategoryList = new CategoryService().selectAllSubCategoryList();
		
		request.setAttribute("sCategoryList", sCategoryList);
		
        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser != null && "admin".equals(loginUser.getMemberId())) {
			request.getRequestDispatcher("views/admin/product/productListView.jsp").forward(request, response);
        } else {
            session.setAttribute("alertMsg", "관리자만 이용 가능한 서비스입니다.");
            response.sendRedirect(request.getContextPath()+"/loginPage.me");
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

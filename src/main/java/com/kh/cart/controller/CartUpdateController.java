package com.kh.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;

/**
 * Servlet implementation class CartUpdateController
 */
@WebServlet("/update.ca")
public class CartUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 1) 요청 시 전달값 뽑기
		
		// 카트번호
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));

		
		// 변경 수량
		int updateCount = Integer.parseInt(request.getParameter("updateCount"));
		
		// System.out.println(updateCount);
		// System.out.println(updateSize);
		
		// 2) vo 로 가공
		// Cart c = new Cart(cartNo, updateSize, updateCount);
		
		Cart c = new Cart(cartNo, updateCount);
		// c.setCartNo(cartNo);
		// c.setUpdateSizeNo(updateSize);
		// c.setUpdateCount(updateCount);
		// System.out.println(c);
		// 3)
		int result = new CartService().updateCart(c);
		
		
		
		// 수정 후 장바구니 페이지로 다시 쏘기
		response.sendRedirect(request.getContextPath() + "/cart.do");
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.kh.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class CartInsertController
 */
@WebServlet("/insert.ca")
public class CartInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		// 로그인한 회원만 볼 수 있게 if문
		if(session.getAttribute("loginUser") == null) {
			// 로그인 전
			session.setAttribute("alertMsg", "로그인한 사용자만 이용 가능합니다.");
			
			response.sendRedirect(request.getContextPath()+"/loginPage.me");
			
		} else {
			// 로그인 후
			String memberId = loginUser.getMemberId();
			
			// productNo sizeNo productPrice
			int productNo = Integer.parseInt(request.getParameter("pno"));
			String sizeName = request.getParameter("sname");
			int productPrice = Integer.parseInt(request.getParameter("price"));
			
			
			Cart c = new Cart(sizeName, memberId, productNo, productPrice);
			
			int result = new CartService().insertCart(c);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "성공적으로 장바구니에 추가되었습니다.");
		
				response.sendRedirect(request.getContextPath() + "/productDetail.pr?pno=" + productNo);
				
			} else {
				request.setAttribute("errorMsg", "장바구니 추가에 실패했습니다.");
				
				response.sendRedirect(request.getContextPath() + "/productDetail.pr?pno=" + productNo);
				
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

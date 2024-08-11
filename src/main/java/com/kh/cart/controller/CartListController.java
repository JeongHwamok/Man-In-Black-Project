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
 * Servlet implementation class CartListController
 */
@WebServlet("/cart.do")
public class CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
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
			
			// System.out.println(memberId);
			
			ArrayList<Cart> list = new CartService().selectCartList(memberId);
			
			// 화면에 보여질 페이지 정보를 응답데이터로 넘기기
			request.setAttribute("list", list);
			
			// 장바구니 화면 포워딩
			request.getRequestDispatcher("views/cart/cartList.jsp").forward(request, response);
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

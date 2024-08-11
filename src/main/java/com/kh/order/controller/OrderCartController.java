package com.kh.order.controller;

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
 * Servlet implementation class OrderCartController
 */
@WebServlet("/order.ca")
public class OrderCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		request.setCharacterEncoding("UTF-8");
		
		
		String memberId = loginUser.getMemberId();
		int total = Integer.parseInt(request.getParameter("total"));
		
		// System.out.println(total);
		// System.out.println(memberId);
		
		ArrayList<Cart> list = new CartService().selectCartList(memberId);
		
		// 화면에 보여질 페이지 정보를 응답데이터로 넘기기
		request.setAttribute("total", total);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/order/orderCart.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

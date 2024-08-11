package com.kh.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;

/**
 * Servlet implementation class CartDeleteController
 */
@WebServlet("/delete.ca")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 카트번호 먼저 뽑기 (cartNo)
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		
		// System.out.println(cartNo);
		
		// 서비스단으로 삭제요청 후 결과 받기
		int result = new CartService().deleteCart(cartNo);
	
		// 결과에 따른 응답페이지
		if(result > 0) { // 성공
			
			// cart.do 로 재요청
			//request.getSession().setAttribute("alertMsg", "성공적으로 삭제되었습니다.");
			
			response.sendRedirect(request.getContextPath() + "/cart.do");
		} else {
			
			// 에러 문구 담아서 에러페이지로 포워딩
			request.setAttribute("errorMsg", "삭제에 실패했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/cart.do");
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

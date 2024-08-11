package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class myPageController
 */
@WebServlet("/myPage.me")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 완료시 마이페이지로 이동을 요청하는 서블릿
		
		HttpSession session = request.getSession();
		Member loginUser =  (Member)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			// 로그인상태일때 마이페이지로 이동
			request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
			
		} else {
			// 로그인상태가 아닐경우 마이페이지 서버로 요청하는걸 방지
			session.setAttribute("alertMsg", "로그인 후 이용 가능합니다.");
			
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

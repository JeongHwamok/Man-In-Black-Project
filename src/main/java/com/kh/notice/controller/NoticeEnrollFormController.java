package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class NoticeEnrollFormController
 */
@WebServlet("/enrollForm.no")
public class NoticeEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 권한체크
//		HttpSession session = request.getSession();
//		
//		if(session.getAttribute("loginUser") != null 
//		   && ((Member)(session.getAttribute("loginUser"))).getUserId().equals("admin")) {
//			// 로그인이 되어있고 admin 일 경우
//			
//			// 공지사항 작성 페이지를 포워딩
//
//			
//		} else {
//			
//			// 메인페이지로 url 재요청
//			session.setAttribute("alertMsg", 
//								 "관리자만 이용 가능한 서비스입니다.");
//			
//			response.sendRedirect(request.getContextPath());
//		}
		RequestDispatcher view 
		= request.getRequestDispatcher("views/notice/noticeEnrollForm.jsp");
	
	view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

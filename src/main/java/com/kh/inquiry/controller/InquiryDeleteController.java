package com.kh.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.inquiry.model.service.InquiryService;

/**
 * Servlet implementation class InquiryDeleteController
 */
@WebServlet("/delete.iq")
public class InquiryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 문의 번호 뽑기
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
		
		// 서비스단 가기
		int result = new InquiryService().deleteInquiry(inquiryNo);
		
		// 결과에 따른 응답페이지
		if(result > 0) {
			
			request.getSession().setAttribute("alertMsg", "문의내역이 삭제되었습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPage.me");
		} else {
			
			request.setAttribute("errorMsg", "삭제에 실패했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPage.me");
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

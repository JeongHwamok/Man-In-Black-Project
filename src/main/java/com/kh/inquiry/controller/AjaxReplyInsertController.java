package com.kh.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.inquiry.model.service.InquiryService;
import com.kh.inquiry.model.vo.Inquiry;

/**
 * Servlet implementation class AjaxReplyInsert
 */
@WebServlet("/rinsert.iq")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ajax 는 post 여도 인코딩 안 해도 됨
		
		// 요청 시 전달값 뽑기
		String replyContent 
			= request.getParameter("replyContent");
		int inquiryNo 
			= Integer.parseInt(request.getParameter("ino"));
		
		// System.out.println(replyContent);
		// System.out.println(inquiryNo);
		
		Inquiry i = new Inquiry();
		i.setInquiryNo(inquiryNo);
		i.setInquiryAnswer(replyContent);
		
		int result = new InquiryService().insertReply(i);
		
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

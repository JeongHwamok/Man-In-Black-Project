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
 * Servlet implementation class InquiryInsertController
 */
@WebServlet("/insert.iq")
public class InquiryInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		String memberId = request.getParameter("memberId");
		String inquiryTitle = request.getParameter("inquiryTitle");
		String inquiryComment =request.getParameter("inquiryComment");
		
		
		Inquiry i = new Inquiry();
		i.setProductNo(productNo);
		i.setMemberId(memberId);
		i.setInquiryTitle(inquiryTitle);
		i.setInquiryComment(inquiryComment);
		
		int result = new InquiryService().insertInquiry(i);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 문의작성 되었습니다.");
			response.sendRedirect(request.getContextPath() + "/productDetail.pr?pno=" + productNo);
		} else {
			request.setAttribute("errorMsg", "문의 작성에 실패했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/productDetail.pr?pno=");
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

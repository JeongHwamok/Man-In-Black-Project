package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.inquiry.model.service.InquiryService;
import com.kh.inquiry.model.vo.Inquiry;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.review.model.service.ReviewService;
import com.kh.review.model.vo.Review;

/**
 * Servlet implementation class productListController
 */
@WebServlet("/inquiryList.ad")
public class AdminInquiryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInquiryListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Inquiry> list = new InquiryService().adminSelectInquiry();
		
		request.setAttribute("list", list);
		
		ArrayList<Member> mlist = new MemberService().adminSelectMemberAll();
		
		request.setAttribute("mlist", mlist);
		
        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser != null && "admin".equals(loginUser.getMemberId())) {
        	request.getRequestDispatcher("views/admin/inquiry/inquiryListView.jsp").forward(request, response);
        } else {
            session.setAttribute("alertMsg", "관리자만 이용 가능한 서비스입니다.");
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

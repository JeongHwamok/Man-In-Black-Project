package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.inquiry.model.vo.Inquiry;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.order.model.vo.Order;
import com.kh.product.model.vo.Product;
import com.kh.review.model.vo.Review;

/**
 * Servlet implementation class AdminMemberSeachController
 */
@WebServlet("/searchInquiry.ad")
public class AdminInquirySearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInquirySearchController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] qStrArr = null;
		HashMap<String, String> qStrMap = new HashMap<String, String>();
		
		// currentPage 쿼리스트링이 없으면 자동으로 1 부여
		// qStrMap.put("currentPage", "1");
		
		// orderby 쿼리스트링이 없으면 자동으로 date 부여
		// qStrMap.put("orderby", "date");
		
		if(request.getQueryString() != null) {
			qStrArr = request.getQueryString().split("&");
			// 모든 쿼리스트링을 qStrMap에 저장
			for(String s : qStrArr) {
				if(s.split("=").length == 2) {
					String key = s.split("=")[0];
					String value = s.split("=")[1];
					
					qStrMap.put(key, value);
				}
			}
		}
		
		String keyword = request.getParameter("searchName");
		if(keyword != null) {
			qStrMap.put("keyword", keyword);
		}
		
		ArrayList<Inquiry> list = new AdminService().searchInquiry(qStrMap);
		
		request.setAttribute("list", list);
		
		ArrayList<Member> mlist = new MemberService().adminSelectMemberAll();
		
		request.setAttribute("mlist", mlist);
		
		
		
		request.getRequestDispatcher("views/admin/inquiry/inquiryListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

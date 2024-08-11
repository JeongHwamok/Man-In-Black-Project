package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.admin.model.service.AdminService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AdminPageController
 */
@WebServlet("/admin.ad")
public class AdminPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        
        int countMember = new AdminService().countMember();
        int countProduct = new AdminService().countProduct();
        int yearSales = new AdminService().sumYearSales();
        HashMap<Integer, String> salesBymonth = new AdminService().sumSalesBymonth();
        
        HashMap<Integer, ArrayList<Integer>> salesTop5 = new AdminService().selectSalesTop5();

        if (loginUser != null && "admin".equals(loginUser.getMemberId())) {
        	request.setAttribute("countMember", countMember);
        	request.setAttribute("countProduct", countProduct);
        	request.setAttribute("yearSales", yearSales);
        	request.setAttribute("salesBymonth", salesBymonth);
        	
        	request.setAttribute("salesTop5", salesTop5);
        	
            request.getRequestDispatcher("views/admin/adminPage.jsp").forward(request, response);
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

package com.kh.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.like.model.vo.Like;
import com.kh.member.model.vo.Member;
import com.kh.product.model.service.ProductService;

/**
 * Servlet implementation class AjaxLikeDeleteController
 */
@WebServlet("/ldelete.pr")
public class AjaxLikeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxLikeDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		
		int MemberNo = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
				
		Like l = new Like(MemberNo, productNo);
		
		int result = new ProductService().deleteLike(l);
		
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

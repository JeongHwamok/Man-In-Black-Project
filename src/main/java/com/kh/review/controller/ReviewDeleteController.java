package com.kh.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewDeleteController
 */
@WebServlet("/delete.re")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 리뷰 번호 먼저 뽑기 (reviewNo)
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		// 서비스단으로 삭제 요청 후 결과 받기
		int result = new ReviewService().deleteReview(reviewNo);
		
		// 결과에 따른 응답페이지
		if(result > 0) {
			
			request.getSession().setAttribute("alertMsg", "성공적으로 리뷰가 삭제되었습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPage.me");
		} else {
			
			System.out.println("실패");
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

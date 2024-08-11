package com.kh.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.review.model.service.ReviewService;
import com.kh.review.model.vo.Review;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/insert.re")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 현재 이 요청이 multipart 형식인지 검사 먼저 진행
			if(ServletFileUpload.isMultipartContent(request)) {
				// 이 요청이 multipart 형식일 경우
				
				// 1. MultipartRequest 객체 생성 시
				//    필요한 설정값들 먼저 셋팅
				// 1_1. 전송용량제한 (int maxSize)
				int maxSize = 10 * 1024 * 1024;
				
				// 1_2. 저장할 폴더의 물리적인 경로 (String savePath)
				String savePath = request.getServletContext().getRealPath("/resources/review_upfiles/");
				
				// 2. MultipartRequest 객체 생성
				// > 파일명 변경됨 파일이 서버에 저장됨,
				//   MultipartRequest 객체까지 생성됨
				MultipartRequest multiRequest
					= new MultipartRequest(request,
										   savePath
										   ,maxSize,
										   "UTF-8",
										   new MyFileRenamePolicy());
				
				// 3. 요청시 전달값 뽑기
				// REVIEW_NO	NUMBER
				// REVIEW_CONTENT	VARCHAR2(300 BYTE)
				// REVIEW_FILE_NAME	VARCHAR2(100 BYTE)
				// REVIEW_FILE_PATH	VARCHAR2(100 BYTE)
				// REVIEW_STATUS	CHAR(1 BYTE)
				// REVIEW_DATE	DATE
				// MEMBER_NO	NUMBER
				// PRODUCT_NO	NUMBER
				
				// 리뷰 번호는 시퀀스로 처리
				
				// 리뷰 내용
				String reviewContent = multiRequest.getParameter("reviewContent");
				
				// 회원 번호
				// int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int memberNo = Integer.parseInt(multiRequest.getParameter("memberNo"));
				// 상품 번호
				// int productNo = Integer.parseInt(request.getParameter("productNo"));
				int productNo = Integer.parseInt(multiRequest.getParameter("productNo"));
				
				// 주문 번호
				// int orderItemNo = Integer.parseInt(request.getParameter("orderItemNo"));
				int orderItemNo = Integer.parseInt(multiRequest.getParameter("orderItemNo"));
				
				// 사진

				
				String key = "reviewUpFile";
				
				Review r = new Review();
				// > 리뷰내용, 리뷰사진수정명, 리뷰사진 경로, 회원번호, 상품번호
				
				// System.out.println(reviewContent);
				// System.out.println(key);
				
				if(multiRequest.getOriginalFileName(key) != null) {
					// 해당 키값에 딸린 첨부파일이 있을 경우

					// 리뷰사진수정명
					String changeName = multiRequest.getFilesystemName(key);
					
					// 경로
					String filePath = "resources/review_upfiles/";
					
					r.setReviewFileName(changeName);
					r.setReviewFilePath(filePath);
					r.setReviewContent(reviewContent);
					r.setMemberNo(memberNo);
					r.setProductNo(productNo);

				}
				
				int result = new ReviewService().insertReview(r);
				
				if(result > 0) {
					request.getSession().setAttribute("alertMsg", "성공적으로 리뷰작성 되었습니다.");
					response.sendRedirect(request.getContextPath() + "/myPage.me");
				} else {
					request.setAttribute("errorMsg", "리뷰 작성에 실패했습니다.");
					
					response.sendRedirect(request.getContextPath() + "/myPage.me");
				}
				
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

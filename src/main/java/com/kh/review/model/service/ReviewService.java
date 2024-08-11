package com.kh.review.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.review.model.dao.ReviewDao;
import com.kh.review.model.vo.Review;

public class ReviewService {

	// 자신이 쓴 리뷰 조회
	public ArrayList<Review> selectMyReview(String memberId) {
		
		// 1) 
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		ArrayList<Review> list = new ReviewDao().selectMyReview(conn, memberId);
		
		// System.out.println(list);
		// 3 트랜잭션 처리
		// > select 문은 패스
		
		// 4 Connection 반납
		JDBCTemplate.close(conn);
		
		// 5
		return list;
		

	}
	
	// 리뷰 작성
	public int insertReview(Review r) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		int result = new ReviewDao().insertReview(conn, r);
	
		// 3)
		if(result > 0) { // 성공
			
			JDBCTemplate.commit(conn);
		} else { // 실패
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	// 리뷰 삭제
	public int deleteReview(int reviewNo) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		int result = new ReviewDao().deleteReview(conn, reviewNo);
		
		// 3)
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	// 상품별 리뷰 조회 
	public ArrayList<Review> selectProductReview(int productNo) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		ArrayList<Review> rList = new ReviewDao().selectProductReview(conn, productNo);
		
		// System.out.println(list);
		// 3) 트랜잭션 처리
		// > select 문은 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5)
		return rList;
	}
	
	// 자신이 쓴 리뷰 조회
	public ArrayList<Review> selectAllReview(int productNo) {
		
		// 1) 
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		ArrayList<Review> list = new ReviewDao().selectAllReview(conn, productNo);
		
		// System.out.println(list);
		// 3 트랜잭션 처리
		// > select 문은 패스
		
		// 4 Connection 반납
		JDBCTemplate.close(conn);
		
		// 5
		return list;
		

	}
	
	// 관리자용 리뷰 조회
	public ArrayList<Review> adminSelectReview(){
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> list = new ReviewDao().adminSelectReview(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	//관리자용 리뷰 수정
	public void adminUpdateReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ReviewDao().adminUpdateReview(conn,r);
		
		if (result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
	
	
}

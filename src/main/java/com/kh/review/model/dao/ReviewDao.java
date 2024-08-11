package com.kh.review.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.inquiry.model.vo.Inquiry;
import com.kh.review.model.vo.Review;

public class ReviewDao {

	private Properties prop = new Properties();
	
	public ReviewDao (){
		
		String fileName
			= ReviewDao.class.getResource("/sql/review/review-mapper.xml").getPath();
		
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	 
	}
	
	// 내 리뷰 조회
	public ArrayList<Review> selectMyReview(Connection conn, String memberId) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Review> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// System.out.println(memberId);
		
		// sql
		String sql = prop.getProperty("selectMyReview");
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				
				Review r = new Review(
									  rset.getInt("REVIEW_NO"),
									  rset.getString("MAX(R.REVIEW_FILE_PATH)"),
									  rset.getString("MAX(P.PRODUCT_NAME)"),
									  rset.getString("MAX(S.SIZE_NAME)"),
									  rset.getInt("MAX(OI.ORDER_ITEM_QUANTITY)"),
									  rset.getDate("MAX(O.ORDER_DATE)"),
									  rset.getDate("MAX(R.REVIEW_DATE)"),
									  rset.getString("MAX(R.REVIEW_CONTENT)"),
									  rset.getInt("MAX(M.MEMBER_NO)"));
				r.setReviewFileName(rset.getString("MAX(R.REVIEW_FILE_NAME)"));
				r.setReviewStatus(rset.getString("MAX(R.REVIEW_STATUS)"));
				
				list.add(r);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		// System.out.println(list);
		
		return list;
		
	}
	
	// 리뷰 작성용 
	public int insertReview(Connection conn, Review r) {
		
		// INSERT 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL 문
		String sql = prop.getProperty("insertReview");
		
		try {
			
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			pstmt.setString(1, r.getReviewContent());
			pstmt.setString(2, r.getReviewFileName());
			pstmt.setString(3, r.getReviewFilePath());
			pstmt.setInt(4, r.getMemberNo());
			pstmt.setInt(5, r.getProductNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
		
	}
	
	// 리뷰 삭제
	public int deleteReview(Connection conn, int reviewNo) {
		
		// UPDATE 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL 문
		String sql = prop.getProperty("deleteReview");
		
		try {
			
			// 1
			pstmt = conn.prepareStatement(sql);
			
			// 2
			pstmt.setInt(1, reviewNo);
			
			// 3
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4)
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}
	
	// 상품별 리뷰 조회용
	public ArrayList<Review> selectProductReview(Connection conn, int productNo) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Review> rList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql
		String sql = prop.getProperty("selectProductReview");
		
		try {
			
			// System.out.println(productNo);
			// 1
			pstmt = conn.prepareStatement(sql);
			
			// 2
			pstmt.setInt(1, productNo);
			
			// 3
			rset = pstmt.executeQuery();
			
			// 4 vo 옮기기
			// REVIEW_NO, REVIEW_CONTENT, REVIEW_FILE_NAME, REVIEW_FILE_PATH
		    // REVIEW_DATE, SUBSTR(M.MEMBER_ID, 1, 3) || '*****' AS MEMBER_ID
			while(rset.next()) {
				
				Review r = new Review(rset.getInt("REVIEW_NO"),
						              rset.getString("REVIEW_CONTENT"),
						              rset.getString("REVIEW_FILE_NAME"),
						              rset.getString("REVIEW_FILE_PATH"),
						              rset.getDate("REVIEW_DATE"),
						              rset.getString("MEMBER_ID"));
				
				rList.add(r);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// System.out.println(rList);
		return rList;
		
	}
	
	// 상품별 리뷰 전체 조회용
	public ArrayList<Review> selectAllReview(Connection conn, int productNo) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Review> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// System.out.println(memberId);
		
		// sql
		String sql = prop.getProperty("selectAllReview");
		
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				
				Review r = new Review(
									  rset.getInt("MAX(R.REVIEW_NO)"),
									  rset.getString("MAX(R.REVIEW_FILE_PATH)"),
									  rset.getString("MAX(P.PRODUCT_NAME)"),
									  rset.getString("MAX(S.SIZE_NAME)"),
									  rset.getInt("MAX(OI.ORDER_ITEM_QUANTITY)"),
									  rset.getDate("MAX(O.ORDER_DATE)"),
									  rset.getDate("MAX(R.REVIEW_DATE)"),
									  rset.getString("MAX(R.REVIEW_CONTENT)"),
									  rset.getInt("MAX(M.MEMBER_NO)"));
				r.setReviewFileName(rset.getString("MAX(R.REVIEW_FILE_NAME)"));
				r.setReviewStatus(rset.getString("MAX(R.REVIEW_STATUS)"));
				
				list.add(r);
				
				// System.out.println(list);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		// System.out.println(list);
		
		return list;
		
	}
	
	// 관리자용 리뷰 조회
		public ArrayList<Review> adminSelectReview(Connection conn) {

		    ArrayList<Review> list = new ArrayList<>();
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;

		    String sql = prop.getProperty("adminSelectReview");

		    try {
		        pstmt = conn.prepareStatement(sql);
		        rset = pstmt.executeQuery();

		        while (rset.next()) {
		            Review r = new Review(
		                rset.getInt("REVIEW_NO"),
		                rset.getString("REVIEW_CONTENT"),
		                rset.getString("REVIEW_FILE_PATH"),
		                rset.getDate("REVIEW_DATE"),
		                rset.getString("REVIEW_STATUS"),
		                rset.getInt("MEMBER_NO"),
		                rset.getInt("PRODUCT_NO")
		            );
		            list.add(r);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        JDBCTemplate.close(rset);
		        JDBCTemplate.close(pstmt);
		    }

		    return list;
		}

		// 관리자용 리뷰상태 수정
		public int adminUpdateReview(Connection conn,Review r) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("adminUpdateReview");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, r.getReviewStatus());
				pstmt.setInt(2, r.getReviewNo());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		
		
		
	

}

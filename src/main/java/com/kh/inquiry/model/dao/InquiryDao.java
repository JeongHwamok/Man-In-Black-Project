package com.kh.inquiry.model.dao;

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

public class InquiryDao {

	private Properties prop = new Properties();
	
	public InquiryDao() {
		
		String fileName
			= InquiryDao.class.getResource("/sql/inquiry/inquiry-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 상품별 문의 리스트 조회용
		public ArrayList<Inquiry> selectInquiry(Connection conn, int productNo) {
			
			// SELECT 문 > ResultSet 객체 (여러행)
			// > ArrayList
			
			// 필요한 변수들 먼저 셋팅
			ArrayList<Inquiry> iList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// sql
			String sql = prop.getProperty("selectInquiry");
			
			try {
				
				// 1) preparedStatement 생성
				pstmt = conn.prepareStatement(sql);
				
				// 2)
				pstmt.setInt(1, productNo);
				
				// 3)
				rset = pstmt.executeQuery();
				
				// 4) vo 로 옮기기
				// INQUIRY_NO, INQUIRY_TITLE, MEMBER_ID, INQUIRY_DATE, PRODUCT_NAME
				while(rset.next()) {
					
					Inquiry i = new Inquiry(
											rset.getInt("INQUIRY_NO"),
											rset.getString("INQUIRY_TITLE"),
											rset.getString("MEMBER_ID"),
											rset.getDate("INQUIRY_DATE"),
											rset.getString("PRODUCT_NAME"));
					
					iList.add(i);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return iList;
			
		}
		
		// 상품별 상세문의 조회
	public ArrayList<Inquiry> selectInquiryDetail(Connection conn, int inquiryNo) {
			
			// SELECT 문 > ResultSet 객체 (여러행)
			// > ArrayList
			
			// 필요한 변수들 먼저 셋팅
			ArrayList<Inquiry> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// sql
			String sql = prop.getProperty("selectInquiryDetail");
			
			try {
				
				// 1) preparedStatement 생성
				pstmt = conn.prepareStatement(sql);
				
				// 2)
				pstmt.setInt(1, inquiryNo);
				
				// 3)
				rset = pstmt.executeQuery();
				
				// 4) vo 로 옮기기
				// INQUIRY_NO, INQUIRY_TITLE, INQUIRY_COMMENT
				// SUBSTR(M.MEMBER_ID, 1, 3) || '*****' AS MEMBER_ID
			    // INQUIRY_DATE, PRODUCT_NAME
				while(rset.next()) {
					
					Inquiry i = new Inquiry(
											rset.getInt("INQUIRY_NO"),
											rset.getString("INQUIRY_TITLE"),
											rset.getString("INQUIRY_COMMENT"),
											rset.getString("MEMBER_ID"),
											rset.getDate("INQUIRY_DATE"),
											rset.getString("PRODUCT_NAME"));
					
					list.add(i);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
			
		}

		// 문의사항 댓글 작성용 메소드
		public int insertReply(Connection conn, Inquiry i) {
			
			// 필요한 변수들 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			// 실행할 쿼리문
			String sql = prop.getProperty("insertReply");
			
			
			try {
				
				// 1
				pstmt = conn.prepareStatement(sql);
				
				// 2
				pstmt.setString(1, i.getInquiryAnswer());
				pstmt.setInt(2, i.getInquiryNo());
				
				// 3
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 4
				JDBCTemplate.close(pstmt);
			}
			
			return result;
			
		}
		
		// 문의 댓글 조회용 메소드
		public ArrayList<Inquiry> selectReplyList(Connection conn, int inquiryNo) {
			
			// SELECT 문 > ResultSet 객체 (여러행)
			// > ArrayList<Reply>
			
			// 필요한 변수들 먼저 셋팅
			ArrayList<Inquiry> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 쿼리문
			String sql = prop.getProperty("selectReplyList");
			
			
			try {
				
				// 1
				pstmt = conn.prepareStatement(sql);
				
				// 2
				pstmt.setInt(1, inquiryNo);
				
				// System.out.println(inquiryNo);
				
				// 3
				rset = pstmt.executeQuery();
				
				// 4
				while(rset.next()) {
					
					Inquiry i = new Inquiry();
					i.setInquiryAnswer(rset.getString("INQUIRY_ANSWER"));
					i.setInquiryAnswerDate(rset.getDate("INQUIRY_ANSWER_DATE"));
					
					list.add(i);
					// System.out.println(i);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 5
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			// 6
			return list;
			
		}
		
		// 상품문의 작성용
		public int insertInquiry(Connection conn, Inquiry i) {
			
			// INSERT 문 > 처리된 행의 갯수 (int)
			
			// 필요한 변수들 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			// 실행할 SQL 문
			String sql = prop.getProperty("insertInquiry");
			
			try {
				
				// 1
				pstmt = conn.prepareStatement(sql);
				
				// 2 
				// inquiryTitle, inquiryComment, memberId
				pstmt.setString(1, i.getInquiryTitle());
				pstmt.setString(2, i.getInquiryComment());
				pstmt.setString(3, i.getMemberId());
				pstmt.setInt(4, i.getProductNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(pstmt);
			}
			
			return result;
			
		}
		
		// 내 문의 조회
		public ArrayList<Inquiry> selectMyInquiry(Connection conn, String memberId) {
			
			// SELECT 문 > ResultSet 객체 (여러행)
			// > ArrayList
			
			// 필요한 변수들 먼저 셋팅
			ArrayList<Inquiry> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// sql
			String sql = prop.getProperty("selectMyInquiry");
			
			try {
				
				// 1
				pstmt = conn.prepareStatement(sql);
				
				// 2
				pstmt.setString(1, memberId);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					// INQUIRY_NO, INQUIRY_TITLE, INQUIRY_COMMENT
				    // INQUIRY_DATE, INQUIRY_ANSWER, PRODUCT_NAME
					Inquiry i = new Inquiry(rset.getInt("INQUIRY_NO"),
										    rset.getString("INQUIRY_TITLE"),
										    rset.getString("INQUIRY_COMMENT"),
										    rset.getDate("INQUIRY_DATE"),
										    rset.getString("INQUIRY_ANSWER"),
										    rset.getString("PRODUCT_NAME"));
					
					list.add(i);
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
			
		}
		
		// 내 문의내역 삭제
	public int deleteInquiry(Connection conn, int inquiryNo) {
		
		// UPDATE 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql
		String sql = prop.getProperty("deleteInquiry");
		
		try {
			
			// 1
			pstmt = conn.prepareStatement(sql);
			
			// 2
			pstmt.setInt(1, inquiryNo);
			
			// 3
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4
			JDBCTemplate.close(pstmt);
		}
		
		// 5
		return result;
		
		
		
	}
	
	// 관리자용 문의 조회
		public ArrayList<Inquiry> adminSelectInquiry(Connection conn) {
			
			ArrayList<Inquiry> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
		
			String sql = prop.getProperty("adminSelectInquiry");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Inquiry i = new Inquiry(rset.getInt("INQUIRY_NO"),
										    rset.getString("INQUIRY_TITLE"),
										    rset.getString("INQUIRY_COMMENT"),
										    rset.getDate("INQUIRY_DATE"),
										    rset.getString("INQUIRY_ANSWER"),
										    rset.getDate("INQUIRY_ANSWER_DATE"),
										    rset.getInt("MEMBER_NO"),
										    rset.getInt("PRODUCT_NO"));
					
					list.add(i);
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
			
		}
		
		// 관리자용 리뷰 수정(답변)
		public int adminUpdateInquiry(Connection conn,Inquiry i) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("adminUpdateInquiry");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,i.getInquiryAnswer());
				pstmt.setInt(2,i.getInquiryNo());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
	
	
	
	
}

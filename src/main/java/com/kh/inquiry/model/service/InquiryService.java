package com.kh.inquiry.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.inquiry.model.dao.InquiryDao;
import com.kh.inquiry.model.vo.Inquiry;

public class InquiryService {

	// 문의내역 상품별 조회용 서비스
	public ArrayList<Inquiry> selectInquiry(int productNo) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		ArrayList<Inquiry> iList = new InquiryDao().selectInquiry(conn, productNo);
		
		// System.out.println(list);
		// 3) 트랜잭션 처리
		// > select 문은 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5)
		return iList;
	}
	
	// 문의내역 상세 조회용 서비스
	public ArrayList<Inquiry> selectInquiryDetail(int inquiryNo) {
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		ArrayList<Inquiry> list = new InquiryDao().selectInquiryDetail(conn, inquiryNo);
		
		// 3) 트랜잭션 처리
		// > select 문은 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5)
		return list;
		
	}
	
	// 문의 댓글 작성 서비스
	public int insertReply(Inquiry i) {
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		int result = new InquiryDao().insertReply(conn, i);
		
		// 3
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	// 문의 댓글 조회용 서비스
	public ArrayList<Inquiry> selectReplyList(int inquiryNo) {
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		ArrayList<Inquiry> list = new InquiryDao().selectReplyList(conn, inquiryNo);
		
		
		// System.out.println(list);
		// 3 패스
		
		// 4
		JDBCTemplate.close(conn);

		// 5
		return list;
	}
	
	// 상품문의 작성용 서비스
	public int insertInquiry(Inquiry i) {
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		int result = new InquiryDao().insertInquiry(conn, i);
		
		// 3
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	// 자신이 쓴 문의 조회
	public ArrayList<Inquiry> selectMyInquiry(String memberId) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		ArrayList<Inquiry> list = new InquiryDao().selectMyInquiry(conn, memberId);
		
		// System.out.println(list);
		// 3 트랜잭션 처리
		// > select 문은 패스
		
		// 4 Connection 반납
		JDBCTemplate.close(conn);
		
		// 5
		return list;
		
	}
	
	// 내 문의내역 삭제
	public int deleteInquiry(int inquiryNo) {
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		int result = new InquiryDao().deleteInquiry(conn, inquiryNo);
		
		// 3
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// 4
		JDBCTemplate.close(conn);
		
		// 5
		return result;
		
	}
	
	
	// 관리자용 문의 조회
		public ArrayList<Inquiry> adminSelectInquiry() {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<Inquiry> list = new InquiryDao().adminSelectInquiry(conn);
			JDBCTemplate.close(conn);
			return list;
			
		}
		//관리자용 문의 수정(답변)
		public void adminUpdateInquiry(Inquiry i) {
			Connection conn = JDBCTemplate.getConnection();
			
			int result = new InquiryDao().adminUpdateInquiry(conn,i);
			
			if (result>0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
		}
	
}

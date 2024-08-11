package com.kh.cart.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cart.model.dao.CartDao;
import com.kh.cart.model.vo.Cart;
import com.kh.common.JDBCTemplate;

public class CartService {

	// 장바구니 조회용 서비스
	public ArrayList<Cart> selectCartList(String memberId) {
		
//		System.out.println(memberId);
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		ArrayList<Cart> list = new CartDao().selectCartList(conn, memberId);
		
		// System.out.println(list);
		// 3) 트랜잭션 처리
		// > select 문은 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5)
		return list;
		
	}
	
	// 장바구니 삭제용 서비스
	public int deleteCart(int cartNo) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2)
		int result = new CartDao().deleteCart(conn, cartNo);
		
		// 3)
		if(result > 0) { // 성공
			
			JDBCTemplate.commit(conn);
			
		} else { // 실패
			
			JDBCTemplate.rollback(conn);
			
		}
		
		// 4)
		JDBCTemplate.close(conn);
		
		// 5)
		return result;
		
	}
	
	// 장바구니 수정용 서비스
	
	public int updateCart(Cart c) {
		
		// 1)
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) DAO 로 회원 정보 변경 요청 후 결과 받기
		// > update 용 메소드 호출
		int result = new CartDao().updateCart(conn, c);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	// 장바구니 추가용 서비스
	public int insertCart(Cart c) {
		
		// 1
		Connection conn = JDBCTemplate.getConnection();
		
		// 2
		int result = new CartDao().insertCart(conn, c);
		
		// 3
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
}

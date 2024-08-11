package com.kh.order.model.service;

import static com.kh.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cart.model.dao.CartDao;
import com.kh.cart.model.vo.Cart;
import com.kh.common.JDBCTemplate;
import com.kh.order.model.dao.OrderDao;
import com.kh.order.model.vo.Order;
import com.kh.order.model.vo.OrderItem;
import com.kh.review.model.dao.ReviewDao;

public class OrderService {

	// 구매내역 리스트 조회용
	public ArrayList<OrderItem> selectOrderItemList(String memberId) {
		
		// 1
		Connection conn = getConnection();
		
		// 2
		ArrayList<OrderItem> list = new OrderDao().selectOrderItemList(conn, memberId);
		
		// System.out.println(list);
		// 3 트랜잭션 처리
		// > select 문은 패스
		
		// 4 Connection 반납
		JDBCTemplate.close(conn);
		
		// 5
		return list;
		
	}
	
		//관리자용 주문내역 전체조회
		public ArrayList<Order> adminSelectOrder() {

			Connection conn = getConnection();
			ArrayList<Order> list = new OrderDao().adminSelectOrder(conn);
			close(conn);
			return list;
		}
		//관리자용 주문내역 수정
		public void adminUpdateOrder(Order o) {
			Connection conn = JDBCTemplate.getConnection();
			
			int result = new OrderDao().adminUpdateOrder(conn,o);
			
			if (result>0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
		}
		
		// 결제 처리용 서비스
		public int insertOrder(Order o, ArrayList<Cart> list) {
			
			Connection conn = getConnection();
			
			// ORDER 테이블 추가 요청
			int result = new OrderDao().insertOrder(conn, o);
			
			// ORDER_ITEM 테이블 추가 요청
			for(int i = 0; i < list.size(); i++) {
				result *= new OrderDao().insertOrderItem(conn, list.get(i));
				result *= new CartDao().deleteCart(conn, list.get(i).getCartNo());
			}
			
			// CART 테이블 삭제 요청
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			return result;
		}
		
		// 구매 후 직전 주문정보 가져오기
		public Order selectPreviousOrder() {

			Connection conn = getConnection();
			
			Order o = new OrderDao().selectPreviousOrder(conn);
			
			return o;
		}
}

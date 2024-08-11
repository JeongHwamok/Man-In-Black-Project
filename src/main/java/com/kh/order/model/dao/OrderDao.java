package com.kh.order.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.cart.model.vo.Cart;
import com.kh.common.JDBCTemplate;
import com.kh.order.model.vo.Order;
import com.kh.order.model.vo.OrderItem;

public class OrderDao {

	private Properties prop = new Properties();
	
	public OrderDao() {
		
		String fileName
			= OrderDao.class.getResource("/sql/order/order-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 구매내역 리스트 조회용
	public ArrayList<OrderItem> selectOrderItemList(Connection conn, String memberId) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<OrderItem> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql
		String sql = prop.getProperty("selectOrderItemList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				OrderItem oi = new OrderItem(
											 rset.getInt("ORDER_ITEM_NO"),
											 rset.getInt("PRODUCT_NO"),
											 rset.getInt("MEMBER_NO"),
											 rset.getInt("ORDER_NO"),
											 rset.getString("IMAGE_PATH"),
											 rset.getString("PRODUCT_NAME"),
											 rset.getInt("ORDER_ITEM_PRICE"),
											 rset.getInt("ORDER_ITEM_QUANTITY"),
											 rset.getDate("ORDER_DATE"),
											 rset.getString("ORDER_STATUS"),
											 rset.getString("MEMBER_ID"),
											 rset.getString("IMAGE_CHANGE_NAME"));
				
				list.add(oi);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return list;
		

		
		
	}
	
	//관리자용 구매내역 전체조회
		public ArrayList<Order> adminSelectOrder(Connection conn) {
			
			ArrayList<Order> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			String sql = prop.getProperty("adminSelectOrder");
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Order o = new Order(
								 rset.getInt("ORDER_NO"),
								 rset.getString("ORDER_STATUS"),
								 rset.getDate("ORDER_DATE"),
								 rset.getString("ORDER_MESSAGE"),
								 rset.getInt("ORDER_TOTAL"),
								 rset.getString("ORDER_CANCLE"),
								 rset.getString("ORDER_CODE"),
								 rset.getString("ORDER_ADDRESS"),
								 rset.getInt("MEMBER_NO")
					);
					list.add(o);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				close(rset);
				close(pstmt);
				
			}
			return list;
			
		}
		public int insertOrder(Connection conn, Order o) {
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertOrder");
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, o.getOrderMessage());
				pstmt.setInt(2, o.getOrderPrice());
				pstmt.setString(3, o.getOrderCode());
				pstmt.setString(4, o.getOrderAddress());
				pstmt.setInt(5, o.getMemberNo());


				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		//관리자용 주문내역 수정
		public int adminUpdateOrder(Connection conn,Order o) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("adminUpdateOrder");
			
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				
		        if (o.getOrderCancle().equals("Y")) {
		            o.setOrderStatus("환불완료");
		        }
		        if (o.getOrderStatus().equals("환불완료")) {
		            o.setOrderCancle("Y");
		        }
				

				pstmt.setString(1, o.getOrderStatus());
				pstmt.setString(2, o.getOrderCancle());
				pstmt.setString(3, o.getOrderAddress());
				pstmt.setInt(4, o.getOrderNo());
				
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		
		// ORDER_ITEM 추가
		public int insertOrderItem(Connection conn, Cart c) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertOrderItem");

			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, c.getCartCount());
				pstmt.setInt(2, c.getCartPrice());
				pstmt.setInt(3, c.getProductNo());
				pstmt.setInt(4, c.getMemberNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		// 구매 후 직전 주문정보 가져오기
		public Order selectPreviousOrder(Connection conn) {
			
			Order o = new Order();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectPreviousOrder");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					o.setOrderNo(rset.getInt("ORDER_NO"));
					o.setOrderStatus(rset.getString("ORDER_STATUS"));
					o.setOrderDate(rset.getDate("ORDER_DATE"));
					o.setOrderMessage(rset.getString("ORDER_MESSAGE"));
					o.setOrderPrice(rset.getInt("ORDER_PRICE"));
					o.setOrderCancle(rset.getString("ORDER_CANCLE"));
					o.setOrderCode(rset.getString("ORDER_CODE"));
					o.setOrderAddress(rset.getString("ORDER_ADDRESS"));
					o.setMemberNo(rset.getInt("MEMBER_NO"));
				}
				
			} catch (SQLException e) {				
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return o;
		}
	
}

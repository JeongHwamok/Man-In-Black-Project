package com.kh.cart.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cart.model.vo.Cart;
import com.kh.common.JDBCTemplate;

public class CartDao {

	private Properties prop = new Properties();
	
	public CartDao() {
		
		String fileName
			= CartDao.class.getResource("/sql/cart/cart-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 장바구니 리스트 조회용
	public ArrayList<Cart> selectCartList(Connection conn, String memberId) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Cart> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql
		String sql = prop.getProperty("selectCartList");
		

		try {
			
			// 1) preparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setString(1, memberId);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// System.out.println(rset);
			
			// 4) rset 으로부터 조회된 결과를 VO로 옮겨담기
			// 상품이미지, 상품명, 옵션(사이즈), 수량, 가격
			while(rset.next()) {
				
				Cart c = new Cart(
								  rset.getInt("CART_NO"),
								  rset.getInt("PRODUCT_NO"),
								  rset.getString("PRODUCT_NAME"),
								  rset.getString("SIZE_NAME"),
								  rset.getInt("CART_COUNT"),
								  rset.getInt("CART_PRICE"),
								  rset.getString("IMAGE_PATH"),
								  rset.getString("IMAGE_CHANGE_NAME"));
				
				c.setMemberNo(rset.getInt("MEMBER_NO"));
				
				list.add(c);
				
			}
			// System.out.println(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}
	
	// 장바구니 삭제용
	public int deleteCart(Connection conn, int cartNo) {
		
		// UPDATE 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql
		String sql = prop.getProperty("deleteCart");
		

		try {
			
			// 1)
			pstmt = conn.prepareStatement(sql);
			
			// 2)
			pstmt.setInt(1, cartNo);
			
			// 3)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4)
			JDBCTemplate.close(pstmt);
		}
		
		// 5)
		return result;
		
	} 
	
	// 장바구니 수정
	public int updateCart(Connection conn, Cart c) {
		
		// UPDATE 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql
		String sql = prop.getProperty("updateCart");
		
		try {
			
			// 1 pstmt
			pstmt = conn.prepareStatement(sql);
			
			// 2 미완성된 sql 완성
			pstmt.setInt(1, c.getUpdateCount());
			pstmt.setInt(2, c.getCartNo());
			
			// 3)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}
	
	// 장바구니 추가용
	public int insertCart(Connection conn, Cart c) {
		
		// UPDATE 문 > 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql
		String sql = prop.getProperty("insertCart");
		// System.out.println(c);
		try {
			
			// 1
			pstmt = conn.prepareStatement(sql);
			
			// 2 sizeNo, memberId, productNo, productPrice
			pstmt.setInt(1, c.getCartPrice());
			pstmt.setString(2, c.getSizeName());
			pstmt.setString(3, c.getMemberId());
			pstmt.setInt(4, c.getProductNo());
			
			// 3
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
		
				
		
	}
	
	
}

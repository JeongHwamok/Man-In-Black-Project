package com.kh.admin.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.inquiry.model.vo.Inquiry;
import com.kh.member.model.vo.Member;
import com.kh.notice.model.vo.Notice;
import com.kh.order.model.vo.Order;
import com.kh.product.model.vo.Product;
import com.kh.review.model.vo.Review;

public class AdminDao {
	
	private Properties prop = new Properties();
		
		public AdminDao() {
			
			String fileName
				= AdminDao.class.getResource("/sql/admin/admin-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 관리자용 회원수 카운트 DAO
	public int countMember(Connection conn) {
		
		int countMember = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				countMember = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return countMember;
		
	}
	
	// 관리자용 상품수 카운트 DAO
	public int countProduct(Connection conn) {
		
		int countProduct = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("countProduct");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				countProduct = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return countProduct;
	}
	

	
	// 관리자용 회원 검색 DAO
	public ArrayList<Member> searchMember(Connection conn, HashMap<String, String> qStrMap) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qStrMap.get("keyword"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberPhone(rset.getString("MEMBER_PHONE"));
				m.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				m.setMemberBirthDate(rset.getDate("MEMBER_BIRTHDATE"));
				m.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				m.setMemberRegDate(rset.getDate("MEMBER_REGDATE"));
				m.setMemberStatus(rset.getString("MEMBER_STATUS"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	
	}
	
	// 관리자용 상품 검색 DAO
	public ArrayList<Product> searchProduct(Connection conn, HashMap<String, String> qStrMap) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchProduct");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qStrMap.get("keyword"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductComment(rset.getString("PRODUCT_COMMENT"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductStock(rset.getInt("PRODUCT_STOCK"));
				p.setProductDate(rset.getDate("PRODUCT_DATE"));
				p.setProductStatus(rset.getString("PRODUCT_STATUS"));
				p.setSizeNo(rset.getString("SIZE_NO"));
				p.setSubcategoryNo(rset.getString("SUBCATEGORY_NO"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	//관리자용 상품검색 DAO
	  public ArrayList<Order> searchOrder(Connection conn, HashMap<String, String> qStrMap) {
	        ArrayList<Order> list = new ArrayList<Order>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;

	        String sql = prop.getProperty("searchOrder");

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, qStrMap.get("keyword"));

	            rset = pstmt.executeQuery();

	            while (rset.next()) {
	                Order o = new Order();
	                o.setOrderNo(rset.getInt("ORDER_NO"));
	                o.setOrderStatus(rset.getString("ORDER_STATUS"));
	                o.setOrderDate(rset.getDate("ORDER_DATE"));
	                o.setOrderMessage(rset.getString("ORDER_MESSAGE"));
	                o.setOrderPrice(rset.getInt("ORDER_PRICE"));
	                o.setOrderCancle(rset.getString("ORDER_CANCLE"));
	                o.setOrderCode(rset.getString("ORDER_CODE"));
	                o.setOrderAddress(rset.getString("ORDER_ADDRESS"));
	                o.setMemberNo(rset.getInt("MEMBER_NO"));

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
	
	 
	// 관리자용 리뷰검색 dao
	    public ArrayList<Review> searchReview(Connection conn, HashMap<String, String> qStrMap) {
	        ArrayList<Review> list = new ArrayList<Review>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;

	        String sql = prop.getProperty("searchReview");

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, qStrMap.get("keyword"));

	            rset = pstmt.executeQuery();

	            while (rset.next()) {
	                Review r = new Review();
	                r.setReviewNo(rset.getInt("REVIEW_NO"));
	                r.setReviewContent(rset.getString("REVIEW_CONTENT"));
	                r.setReviewFilePath(rset.getString("REVIEW_FILE_PATH"));
	                r.setReviewDate(rset.getDate("REVIEW_DATE"));
	                r.setMemberNo(rset.getInt("MEMBER_NO"));
	                r.setProductNo(rset.getInt("PRODUCT_NO"));
	                r.setReviewStatus(rset.getString("REVIEW_STATUS"));

	                list.add(r);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rset);
	            close(pstmt);
	        }

	        return list;
	    }
	// 관리자용 문의검색 DAO
	    public ArrayList<Inquiry> searchInquiry(Connection conn, HashMap<String, String> qStrMap) {
	        ArrayList<Inquiry> list = new ArrayList<Inquiry>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;

	        String sql = prop.getProperty("searchInquiry");

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, qStrMap.get("keyword"));

	            rset = pstmt.executeQuery();

	            while (rset.next()) {
	                Inquiry i = new Inquiry();
	                i.setInquiryNo(rset.getInt("INQUIRY_NO"));
	                i.setInquiryTitle(rset.getString("INQUIRY_TITLE"));
	                i.setInquiryComment(rset.getString("INQUIRY_COMMENT"));
	                i.setInquiryDate(rset.getDate("INQUIRY_DATE"));
	                i.setInquiryAnswer(rset.getString("INQUIRY_ANSWER"));
	                i.setInquiryAnswerDate(rset.getDate("INQUIRY_ANSWER_DATE"));
	                i.setMemberNo(rset.getInt("MEMBER_NO"));
	                i.setProductNo(rset.getInt("PRODUCT_NO"));

	                list.add(i);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rset);
	            close(pstmt);
	        }

	        return list;
	    }
	// 관리자용 공지검색
	public ArrayList<Notice> searchNotice(Connection conn, HashMap<String, String> qStrMap) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchNotice");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qStrMap.get("keyword"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeType(rset.getString("NOTICE_TYPE"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				n.setNoticeHit(rset.getInt("NOTICE_HIT"));
				n.setNoticeRDate(rset.getDate("NOTICE_RDATE"));
				n.setNoticeStatus(rset.getString("NOTICE_STATUS"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	// 당해 매출액 계산
	public int sumYearSales(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("sumYearSales");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("총매출");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public HashMap sumSalesBymonth(Connection conn) {
		
		HashMap<String, Integer> salesBymonth = new HashMap<String, Integer>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("salesBymonth");
		
		for(int i = 1; i <= 12; i++) {
			salesBymonth.put(String.valueOf(i), 0);
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				salesBymonth.put(rset.getString("월"), rset.getInt("매출액"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return salesBymonth;
	}
	public HashMap<Integer, ArrayList<Integer>> selectSalesTop5(Connection conn) {
		HashMap<Integer, ArrayList<Integer>> salesTop5 = new HashMap<Integer, ArrayList<Integer>>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSalesTop5");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			int i = 1;
			while(rset.next()) {
				ArrayList<Integer> result = new ArrayList<Integer>();
				result.add(rset.getInt("PRODUCT_NO"));
				result.add(rset.getInt("상품별 매출액"));
				salesTop5.put(i, result);
				i += 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return salesTop5;
	}

}

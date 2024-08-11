package com.kh.admin.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.admin.model.dao.AdminDao;
import com.kh.inquiry.model.vo.Inquiry;
import com.kh.member.model.vo.Member;
import com.kh.notice.model.vo.Notice;
import com.kh.order.model.vo.Order;
import com.kh.product.model.vo.Product;
import com.kh.review.model.vo.Review;


public class AdminService {
	
	// 관리자용 회원수 카운트 서비스
	public int countMember() {
		
		Connection conn = getConnection();
		
		int countMember = new AdminDao().countMember(conn);
		
		close(conn);
		
		return countMember;
	}
	
	// 관리자용 상품수 카운트 서비스
	public int countProduct() {

		Connection conn = getConnection();
		
		int countProduct = new AdminDao().countProduct(conn);
		
		close(conn);
		
		return countProduct;
	}
	

	
	// 관리자용 회원 검색 서비스
	public ArrayList<Member> searchMember(HashMap<String, String> qStrMap) {
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = new AdminDao().searchMember(conn, qStrMap);
		
		close(conn);
		
		return list;
	}
	
	// 관리자용 상품 검색 서비스
	public ArrayList<Product> searchProduct(HashMap<String, String> qStrMap) {
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new AdminDao().searchProduct(conn, qStrMap);
		
		close(conn);
		
		return list;
	}
	
	//관리자용 주문 검색 서비스
	public ArrayList<Order> searchOrder(HashMap<String, String> qStrMap) {
		
		Connection conn = getConnection();
		
		ArrayList<Order> list = new AdminDao().searchOrder(conn, qStrMap);
		
		close(conn);
		
		return list;
	}
	
	//관리자용 리뷰 검색 서비스
	public ArrayList<Review> searchReview(HashMap<String, String> qStrMap) {
		
		Connection conn = getConnection();
		
		ArrayList<Review> list = new AdminDao().searchReview(conn, qStrMap);
		
		close(conn);
		
		return list;
	}
	
	//관리자용 문의답변 검색 서비스
	public ArrayList<Inquiry> searchInquiry(HashMap<String, String> qStrMap) {
		
		Connection conn = getConnection();
		
		ArrayList<Inquiry> list = new AdminDao().searchInquiry(conn, qStrMap);
		
		close(conn);
		
		return list;
	}
	

	//관리자용 공지 검색 서비스
	public ArrayList<Notice> searchNotice(HashMap<String, String> qStrMap) {

		Connection conn = getConnection();
		
		ArrayList<Notice> list = new AdminDao().searchNotice(conn, qStrMap);
		
		close(conn);
		
		return list;
	}

	public int sumYearSales() {
		
		Connection conn = getConnection();
		
		int yearSales = new AdminDao().sumYearSales(conn);
		
		close(conn);
		
		return yearSales;
	}

	public HashMap sumSalesBymonth() {
		
		Connection conn = getConnection();
		
		HashMap salesBymonth = new AdminDao().sumSalesBymonth(conn);
		
		close(conn);
		
		return salesBymonth;
	}
	
	public HashMap<Integer, ArrayList<Integer>> selectSalesTop5() {
		
		Connection conn = getConnection();
		
		HashMap<Integer, ArrayList<Integer>> salesBymonth = new AdminDao().selectSalesTop5(conn);
		
		close(conn);
		
		return salesBymonth;
	}

}

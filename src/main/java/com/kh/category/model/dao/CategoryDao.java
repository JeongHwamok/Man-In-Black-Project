package com.kh.category.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.category.model.vo.Category;
import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;

public class CategoryDao {

private Properties prop = new Properties(); 
	
	// 기본생성자 안에
	// member-mapper.xml 파일로부터 쿼리문들을
	// 키 + 밸류 세트로 읽어들이는 공통코드 작성
	
	public CategoryDao() {
		
		// 읽어들일 member-mapper.xml 파일의 물리적인 경로
		String fileName = MemberDao.class.getResource("/sql/category/category-mapper.xml").getPath();
		// getPath() 해당파일의 경로를 문자열로 바꿔줌
		// C:\05_Web-workspace2\JSP_Project\src\main\webapp\WEB-INF\classes\sql\member\member-mapper.xml
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 카테고리 대분류 조회용 메소드
public ArrayList<Category> selectCategoryList(Connection conn) {
		
		ArrayList<Category> list = new ArrayList<> (); 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Category c = new Category(rset.getInt("CATEGORY_NO"),
										 rset.getString("CATEGORY_NAME"));
										 
			
				list.add(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return list;
	}
	
	// 카테고리 대분류 조회용 메소드
	public ArrayList<Category> selectSubCategoryList(Connection conn, int categoryNo) {
		
		ArrayList<Category> list = new ArrayList<> (); 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSubCategoryList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Category c = new Category(rset.getInt("CATEGORY_NO"),
										 rset.getString("CATEGORY_NAME"),
										 rset.getInt("SUBCATEGORY_NO"),
										 rset.getString("SUBCATEGORY_NAME"));
			
				list.add(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return list;
	}
	
	// 카테고리 대분류 상세조회 메소드
		public Category selectCategory(Connection conn, int categoryNo) {
			
			Category c = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectCategory");
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, categoryNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					c = new Category(rset.getInt("CATEGORY_NO"),
									rset.getString("CATEGORY_NAME"));
											 
				
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			
			return c;
		}
		
		// 카테고리 소분류 상세조회 메소드
		public Category selectSubCategory(Connection conn, int subCategoryNo) {
			
			Category sc = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectSubCategory");
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, subCategoryNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					sc = new Category();
					
					sc.setSubCategoryNo(rset.getInt("SUBCATEGORY_NO"));
					sc.setSubCategoryName(rset.getString("SUBCATEGORY_NAME"));
											 
				
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			
			return sc;
		}
		
		
		public ArrayList<Category> selectAllSubCategoryList(Connection conn) {
			ArrayList<Category> list = new ArrayList<> (); 
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectAllSubCategoryList");
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Category c = new Category();
					c.setSubCategoryNo(rset.getInt("SUBCATEGORY_NO"));
					c.setSubCategoryName(rset.getString("SUBCATEGORY_NAME"));
					c.setCategoryNo(rset.getInt("CATEGORY_NO"));
					c.setCategoryName(rset.getString("CATEGORY_NAME"));
				
					list.add(c);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			
			return list;
		}		
}

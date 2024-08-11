package com.kh.category.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.category.model.dao.CategoryDao;
import com.kh.category.model.vo.Category;
import com.kh.common.JDBCTemplate;

public class CategoryService {
	// 카테고리 대분류 목록 조회용 서비스
	public ArrayList<Category> selectCategoryList () {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Category> list = new CategoryDao().selectCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	// 카테고리 소분류 목록 조회용 서비스
	public ArrayList<Category> selectSubCategoryList (int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Category> list = new CategoryDao().selectSubCategoryList(conn, categoryNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	// 카테고리 상세 조회용 서비스
	public Category selectCategory (int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Category c = new CategoryDao().selectCategory(conn, categoryNo);
		
		JDBCTemplate.close(conn);
		
		return c;
	}
	
	
	public Category selectSubCategory (int subCategoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Category sc = new CategoryDao().selectSubCategory(conn, subCategoryNo);
		
		JDBCTemplate.close(conn);
		
		return sc;
	}

	public ArrayList<Category> selectAllSubCategoryList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Category> list = new CategoryDao().selectAllSubCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
}

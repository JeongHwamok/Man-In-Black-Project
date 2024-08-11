package com.kh.category.model.vo;

public class Category {

	private int categoryNo;			//	CATEGORY_NO	NUMBER					// 카테고리번호-대분류
	private String categoryName;	//	CATEGORY_NAME	VARCHAR2(60 BYTE)	// 카테고리이름-대분류
	private int subCategoryNo;		// 서브카테고리번호
	private String subCategoryName; // 서브카테고리이름을 담을 상자
	
	
	public Category() { }

	
	
	public Category(int categoryNo, String categoryName, int subCategoryNo, String subCategoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.subCategoryNo = subCategoryNo;
		this.subCategoryName = subCategoryName;
	}



	public Category(int categoryNo, String categoryName, String subCategoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
	}


	public Category(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	public String getSubCategoryName() {
		return subCategoryName;
	}


	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	

	public int getSubCategoryNo() {
		return subCategoryNo;
	}



	public void setSubCategoryNo(int subCategoryNo) {
		this.subCategoryNo = subCategoryNo;
	}



	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", subCategoryNo="
				+ subCategoryNo + ", subCategoryName=" + subCategoryName + "]";
	}



	
	
	
}

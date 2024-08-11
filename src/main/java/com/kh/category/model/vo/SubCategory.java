package com.kh.category.model.vo;

public class SubCategory {

	private int subCategoryNo;		//	SUBCATEGORY_NO	NUMBER
	private String subCategoryName;	//	SUBCATEGORY_NAME	VARCHAR2(60 BYTE)
	private int categoryNo;			//	CATEGORY_NO	NUMBER
	
	public SubCategory() { }

	public SubCategory(int subCategoryNo, String subCategoryName, int categoryNo) {
		super();
		this.subCategoryNo = subCategoryNo;
		this.subCategoryName = subCategoryName;
		this.categoryNo = categoryNo;
	}

	public int getSubCategoryNo() {
		return subCategoryNo;
	}

	public void setSubCategoryNo(int subCategoryNo) {
		this.subCategoryNo = subCategoryNo;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "SubCategory [subCategoryNo=" + subCategoryNo + ", subCategoryName=" + subCategoryName + ", categoryNo="
				+ categoryNo + "]";
	}
	
	
	
}

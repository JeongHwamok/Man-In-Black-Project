package com.kh.product.model.vo;

import java.sql.Date;

public class Product {

	private int productNo;			//	PRODUCT_NO	NUMBER 					// 상품번호
	private String productName;		//	PRODUCT_NAME	VARCHAR2(60 BYTE) 	// 이름
	private String productComment;	//	PRODUCT_COMMENT	VARCHAR2(3000 BYTE) // 설명
	private int productPrice;		//	PRODUCT_PRICE	NUMBER 				// 가격
	private int productStock;		//	PRODUCT_STOCK	NUMBER 				// 재고
	private Date productDate;		//	PRODUCT_DATE	DATE 				// 추가일
	private String productStatus;	//	PRODUCT_STATUS	CHAR(1 BYTE) 		// 삭제여부
	private String sizeNo;				//	SIZE_NO	NUMBER 		  			
	// 사이즈 번호 대신 사이즈네임으로 받을거임	
	private String categoryNo;  // 카테고리 대분류 담을 상자 
	private String subcategoryNo;		//	SUBCATEGORY_NO	NUMBER 				
	// 카테고리번호-소분류 번호 대신 카테고리_소분류네임으로 받을거임
	private String titleImg; // 썸네일 이미지 경로 + 수정파일명
	private int imageLevel; // 썸네일 이미지냐 상세이미지냐 구분
	
	public Product() { }

	public Product(int productNo, String productName, String productComment, int productPrice, int productStock,
			Date productDate, String productStatus, String sizeNo, String categoryNo, String subcategoryNo,
			String titleImg) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productComment = productComment;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productDate = productDate;
		this.productStatus = productStatus;
		this.sizeNo = sizeNo;
		this.categoryNo = categoryNo;
		this.subcategoryNo = subcategoryNo;
		this.titleImg = titleImg;
	}
	

	
	
	public Product(int productNo, String productName, String productComment, int productPrice, int productStock,
			Date productDate, String productStatus, String sizeNo, String categoryNo, String subcategoryNo,
			String titleImg, int imageLevel) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productComment = productComment;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productDate = productDate;
		this.productStatus = productStatus;
		this.sizeNo = sizeNo;
		this.categoryNo = categoryNo;
		this.subcategoryNo = subcategoryNo;
		this.titleImg = titleImg;
		this.imageLevel = imageLevel;
	}

	public int getImageLevel() {
		return imageLevel;
	}

	public void setImageLevel(int imageLevel) {
		this.imageLevel = imageLevel;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductComment() {
		return productComment;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getSizeNo() {
		return sizeNo;
	}

	public void setSizeNo(String sizeNo) {
		this.sizeNo = sizeNo;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getSubcategoryNo() {
		return subcategoryNo;
	}

	public void setSubcategoryNo(String subcategoryNo) {
		this.subcategoryNo = subcategoryNo;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productComment=" + productComment
				+ ", productPrice=" + productPrice + ", productStock=" + productStock + ", productDate=" + productDate
				+ ", productStatus=" + productStatus + ", sizeNo=" + sizeNo + ", categoryNo=" + categoryNo
				+ ", subcategoryNo=" + subcategoryNo + ", titleImg=" + titleImg + ", imageLevel=" + imageLevel + "]";
	}

	
	

	

	
	
	
	
	

	
	
	
	
}

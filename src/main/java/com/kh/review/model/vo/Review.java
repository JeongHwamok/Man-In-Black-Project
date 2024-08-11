package com.kh.review.model.vo;

import java.sql.Date;

public class Review {

	private int reviewNo;			//	REVIEW_NO	NUMBER						// 리뷰번호
	private String reviewContent;	//	REVIEW_CONTENT	VARCHAR2(300 BYTE)		// 내용
	private String reviewFileName;	//	REVIEW_FILE_NAME	VARCHAR2(100 BYTE)	// 리뷰이미지수정명
	private String reviewFilePath;	//	REVIEW_FILE_PATH	VARCHAR2(100 BYTE)	// 파일경로
	private Date reviewDate;		//	REVIEW_DATE	DATE						// 작성일
	private int memberNo;			//	MEMBER_NO	NUMBER						// 회원번호
	private int productNo;			//	PRODUCT_NO	NUMBER						// 상품번호
	
	// 추가
	private String productName;
	private String sizeName;
	private int orderItemQuantity;
	private Date orderDate;
	private String reviewStatus;
	private String memberId;
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Review() { }

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	// 전체
	public Review(int reviewNo, String reviewContent, String reviewFileName, String reviewFilePath, Date reviewDate,
			int memberNo, int productNo, String productName, String sizeName, int orderItemQuantity, Date orderDate,
			String reviewStatus) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.reviewFileName = reviewFileName;
		this.reviewFilePath = reviewFilePath;
		this.reviewDate = reviewDate;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.productName = productName;
		this.sizeName = sizeName;
		this.orderItemQuantity = orderItemQuantity;
		this.orderDate = orderDate;
		this.reviewStatus = reviewStatus;
	}
	
	
	// 관리자용 리뷰조회
	public Review(int reviewNo, String reviewContent , String reviewFilePath,
			Date reviewDate,String reviewStatus ,int memberNo,int productNo) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.reviewFilePath = reviewFilePath;
		this.reviewDate = reviewDate;
		this.reviewStatus = reviewStatus;
		this.memberNo = memberNo;
		this.productNo = productNo;
	}
	
	// 내 리뷰 조회 쿼리문
	public Review(int reviewNo, String reviewFilePath, String productName, 
					String sizeName, int orderItemQuantity, 
					Date orderDate, Date reviewDate, String reviewContent, int memberNo) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.reviewFilePath = reviewFilePath;
		this.reviewDate = reviewDate;
		this.productName = productName;
		this.sizeName = sizeName;
		this.orderItemQuantity = orderItemQuantity;
		this.orderDate = orderDate;
		this.memberNo = memberNo;
	}

	// 리뷰 작성용
	public Review(String reviewContent, String reviewFileName, String reviewFilePath, int memberNo, int productNo) {
		super();
		this.reviewContent = reviewContent;
		this.reviewFileName = reviewFileName;
		this.reviewFilePath = reviewFilePath;
		this.memberNo = memberNo;
		this.productNo = productNo;
	}
	
	// 상품별 리뷰 조회
	// REVIEW_NO, REVIEW_CONTENT, REVIEW_FILE_NAME, REVIEW_FILE_PATH
    // REVIEW_DATE, SUBSTR(M.MEMBER_ID, 1, 3) || '*****' AS MEMBER_ID
	public Review(int reviewNo, String reviewContent, String reviewFileName, String reviewFilePath, Date reviewDate,
			String memberId) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.reviewFileName = reviewFileName;
		this.reviewFilePath = reviewFilePath;
		this.reviewDate = reviewDate;
		this.memberId = memberId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewFileName() {
		return reviewFileName;
	}

	public void setReviewFileName(String reviewFileName) {
		this.reviewFileName = reviewFileName;
	}

	public String getReviewFilePath() {
		return reviewFilePath;
	}

	public void setReviewFilePath(String reviewFilePath) {
		this.reviewFilePath = reviewFilePath;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	
	public String getReviewStatus() {
		return reviewStatus;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewFileName="
				+ reviewFileName + ", reviewFilePath=" + reviewFilePath + ", reviewDate=" + reviewDate + ", memberNo="
				+ memberNo + ", productNo=" + productNo + "]";
	}
	
	
	
}

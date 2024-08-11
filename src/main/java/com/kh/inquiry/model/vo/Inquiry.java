package com.kh.inquiry.model.vo;

import java.sql.Date;

public class Inquiry {

	private int inquiryNo;				//	INQUIRY_NO	NUMBER					// 문의번호
	private String inquiryTitle;		//	INQUIRY_TITLE	VARCHAR2(60 BYTE)	// 문의제목
	private String inquiryComment;		//	INQUIRY_COMMENT	VARCHAR2(300 BYTE)	// 문의내용
	private Date inquiryDate;			//	INQUIRY_DATE	DATE				// 문의일
	private String inquiryAnswer;		//	INQUIRY_ANSWER	VARCHAR2(300 BYTE)	// 답변내용
	private Date inquiryAnswerDate;		//	INQUIRY_ANSWER_DATE	DATE			// 답변일
	private int memberNo;				//	MEMBER_NO	NUMBER					// 회원번호
	private int productNo;				//	PRODUCT_NO	NUMBER					// 상품번호
	
	
	// 추가
	private String memberId;
	private String productName;
	
	public Inquiry() { }
	
	// 전체 생성부
	public Inquiry(int inquiryNo, String inquiryTitle, String inquiryComment, Date inquiryDate, String inquiryAnswer,
			Date inquiryAnswerDate, int memberNo, int productNo, String memberId, String productName) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquiryTitle = inquiryTitle;
		this.inquiryComment = inquiryComment;
		this.inquiryDate = inquiryDate;
		this.inquiryAnswer = inquiryAnswer;
		this.inquiryAnswerDate = inquiryAnswerDate;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.memberId = memberId;
		this.productName = productName;
	}
	
	// 상품별 문의 리스트 조회용 생성부
	// INQUIRY_NO, INQUIRY_TITLE, MEMBER_ID, INQUIRY_DATE, PRODUCT_NAME
	public Inquiry(int inquiryNo, String inquiryTitle, String memberId, Date inquiryDate, String productName) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquiryTitle = inquiryTitle;
		this.inquiryDate = inquiryDate;
		this.memberId = memberId;
		this.productName = productName;
	}

	// 상품문의 상세조회 생성부
	// INQUIRY_NO, INQUIRY_TITLE, INQUIRY_COMMENT
	// SUBSTR(M.MEMBER_ID, 1, 3) || '*****' AS MEMBER_ID
    // INQUIRY_DATE, PRODUCT_NAME
	public Inquiry(int inquiryNo, String inquiryTitle, String inquiryComment, String memberId, 
			Date inquiryDate, String productName) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquiryTitle = inquiryTitle;
		this.inquiryComment = inquiryComment;
		this.inquiryDate = inquiryDate;
		this.memberId = memberId;
		this.productName = productName;
	}
	
	// 내 문의 조회 생성부
	public Inquiry(int inquiryNo, String inquiryTitle, String inquiryComment, Date inquiryDate, String inquiryAnswer,
			String productName) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquiryTitle = inquiryTitle;
		this.inquiryComment = inquiryComment;
		this.inquiryDate = inquiryDate;
		this.inquiryAnswer = inquiryAnswer;
		this.productName = productName;
	}
	//관리자용 리뷰조회 생성자
    public Inquiry(int inquiryNo, String inquiryTitle, 
    		String inquiryComment, Date inquiryDate, 
    		String inquiryAnswer, Date inquiryAnswerDate, 
    		int memberNo, int productNo) {
    	
        this.inquiryNo = inquiryNo;
        this.inquiryTitle = inquiryTitle;
        this.inquiryComment = inquiryComment;
        this.inquiryDate = inquiryDate;
        this.inquiryAnswer = inquiryAnswer;
        this.inquiryAnswerDate = inquiryAnswerDate;
        this.memberNo = memberNo;
        this.productNo = productNo;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getInquiryNo() {
		return inquiryNo;
	}

	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}

	public String getInquiryTitle() {
		return inquiryTitle;
	}

	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}

	public String getInquiryComment() {
		return inquiryComment;
	}

	public void setInquiryComment(String inquiryComment) {
		this.inquiryComment = inquiryComment;
	}

	public Date getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public String getInquiryAnswer() {
		return inquiryAnswer;
	}

	public void setInquiryAnswer(String inquiryAnswer) {
		this.inquiryAnswer = inquiryAnswer;
	}

	public Date getInquiryAnswerDate() {
		return inquiryAnswerDate;
	}

	public void setInquiryAnswerDate(Date inquiryAnswerDate) {
		this.inquiryAnswerDate = inquiryAnswerDate;
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

	@Override
	public String toString() {
		return "Inquiry [inquiryNo=" + inquiryNo + ", inquiryTitle=" + inquiryTitle + ", inquiryComment="
				+ inquiryComment + ", inquiryDate=" + inquiryDate + ", inquiryAnswer=" + inquiryAnswer
				+ ", inquiryAnswerDate=" + inquiryAnswerDate + ", memberNo=" + memberNo + ", productNo=" + productNo
				+ "]";
	}
	
	
}

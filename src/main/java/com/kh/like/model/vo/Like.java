package com.kh.like.model.vo;

public class Like {

	private int memberNo;	//	MEMBER_NO	NUMBER	// 회원번호
	private int productNo;	//	PRODUCT_NO	NUMBER	// 상품번호
	
	public Like() { }

	public Like(int memberNo, int productNo) {
		super();
		this.memberNo = memberNo;
		this.productNo = productNo;
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
		return "Like [memberNo=" + memberNo + ", productNo=" + productNo + "]";
	}
	
	
	
}

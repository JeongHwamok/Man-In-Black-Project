package com.kh.order.model.vo;

import java.sql.Date;

public class OrderItem {

	private int orderItemNo;		//	ORDER_ITEM_NO	NUMBER		// 주문상세번호
	private int orderItemQuantity;	//	ORDER_ITEM_QUANTITY	NUMBER	// 주문수량
	private int orderItemPrice;		//	ORDER_ITEM_PRICE	NUMBER	// 한개당주문금액
	private int productNo;			//	PRODUCT_NO	NUMBER			// 상품번호
	private int orderNo;			//	ORDER_NO	NUMBER			// 주문번호
	private int memberNo;			//	MEMBER_NO	NUMBER			// 회원번호
	
	// 필드 추가
	private String imagePath;
	private String productName;
	private Date orderDate;
	private String orderStatus;
	private String memberId;
	private String imageChangeName;
	
	public OrderItem() { }
	
	public OrderItem(int orderItemNo, int orderItemQuantity, int orderItemPrice, int productNo, int orderNo,
			int memberNo) {
		super();
		this.orderItemNo = orderItemNo;
		this.orderItemQuantity = orderItemQuantity;
		this.orderItemPrice = orderItemPrice;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.memberNo = memberNo;
	}
	
	// 주문내역 조회용 생성부
	public OrderItem(int orderItemNo, int productNo, int memberNo, int orderNo,
			String imagePath, String productName, int orderItemPrice, int orderItemQuantity,
			Date orderDate, String orderStatus, String memberId, String imageChangeName) {
		super();
		this.orderItemNo = orderItemNo;
		this.orderItemQuantity = orderItemQuantity;
		this.orderItemPrice = orderItemPrice;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.memberNo = memberNo;
		this.imagePath = imagePath;
		this.productName = productName;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.memberId = memberId;
		this.imageChangeName = imageChangeName;
	}
	
	public String getImageChangeName() {
		return imageChangeName;
	}

	public void setImageChangeName(String imageChangeName) {
		this.imageChangeName = imageChangeName;
	}


	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(int orderItemNo) {
		this.orderItemNo = orderItemNo;
	}

	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public int getOrderItemPrice() {
		return orderItemPrice;
	}

	public void setOrderItemPrice(int orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemNo=" + orderItemNo + ", orderItemQuantity=" + orderItemQuantity
				+ ", orderItemPrice=" + orderItemPrice + ", productNo=" + productNo + ", orderNo=" + orderNo
				+ ", memberNo=" + memberNo + "]";
	}
	
	
	
}

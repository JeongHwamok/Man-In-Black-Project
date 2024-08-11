package com.kh.order.model.vo;

import java.sql.Date;

public class Order {
	
	private int orderNo;			//	ORDER_NO	NUMBER					// 주문번호
	private String orderStatus;		//	ORDER_STATUS	VARCHAR2(20 BYTE)	// 주문상태
	private Date orderDate;			//	ORDER_DATE	DATE					// 주문일
	private String orderMessage;	//	ORDER_MESSAGE	VARCHAR2(100 BYTE)	// 배송요청사항
	private int orderPrice;			//	ORDER_PRICE	NUMBER					// 주문총액
	private String orderCancle;		//	ORDER_CANCLE	CHAR(1 BYTE)		// 환불여부
	private String orderCode;		//	ORDER_CODE	VARCHAR2(100 BYTE)		// 결제코드
	private String orderAddress;	//	ORDER_ADDRESS	VARCHAR2(300 BYTE)	// 배송지
	private int memberNo;			//	MEMBER_NO	NUMBER					// 회원번호
	
	public Order() { }

	public Order(int orderNo, String orderStatus, Date orderDate, String orderMessage, int orderPrice,
			String orderCancle, String orderCode, String orderAddress, int memberNo) {
		super();
		this.orderNo = orderNo;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderMessage = orderMessage;
		this.orderPrice = orderPrice;
		this.orderCancle = orderCancle;
		this.orderCode = orderCode;
		this.orderAddress = orderAddress;
		this.memberNo = memberNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderMessage() {
		return orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderCancle() {
		return orderCancle;
	}

	public void setOrderCancle(String orderCancle) {
		this.orderCancle = orderCancle;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
				+ ", orderMessage=" + orderMessage + ", orderPrice=" + orderPrice + ", orderCancle=" + orderCancle
				+ ", orderCode=" + orderCode + ", orderAddress=" + orderAddress + ", memberNo=" + memberNo + "]";
	}
	
	
	
}

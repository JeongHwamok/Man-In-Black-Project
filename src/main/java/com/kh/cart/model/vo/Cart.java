package com.kh.cart.model.vo;

public class Cart {

	private int cartNo;		//	CART_NO	NUMBER		// 장바구니 번호
	private int cartCount;	//	CART_COUNT	NUMBER	// 상품수량
	private int cartPrice;	//	CART_PRICE	NUMBER	// 하나가격
	private int sizeNo;
	private int memberNo;	//	MEMBER_NO	NUMBER	// 회원번호
	private int productNo;	//	PRODUCT_NO	NUMBER	// 상품번호
	
	// 필드 추가
	private String productName;
	private String sizeName;
	private String imagePath;

	private int updateCount;
	private String imageChangeName;
	private int productPrice;
	private String memberId;
	
	public Cart() { }
	
	
	

	public Cart(int cartNo, int cartCount, int cartPrice, int sizeNo, int memberNo, int productNo, String productName,
			String sizeName, String imagePath, int updateCount, String imageChangeName, int productPrice,
			String memberId) {
		super();
		this.cartNo = cartNo;
		this.cartCount = cartCount;
		this.cartPrice = cartPrice;
		this.sizeNo = sizeNo;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.productName = productName;
		this.sizeName = sizeName;
		this.imagePath = imagePath;
		this.updateCount = updateCount;
		this.imageChangeName = imageChangeName;
		this.productPrice = productPrice;
		this.memberId = memberId;
	}



	public Cart(int cartNo, int cartCount, int cartPrice, int memberNo, int productNo, String productName,
			String sizeName, String imagePath, int updateCount, String memberId) {
		super();
		this.cartNo = cartNo;
		this.cartCount = cartCount;
		this.cartPrice = cartPrice;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.productName = productName;
		this.sizeName = sizeName;
		this.imagePath = imagePath;
		this.updateCount = updateCount;
		this.memberId = memberId;
	}
	
	public Cart(int cartNo, int cartCount, int cartPrice, int memberNo, int productNo) {
		super();
		this.cartNo = cartNo;
		this.cartCount = cartCount;
		this.cartPrice = cartPrice;
		this.memberNo = memberNo;
		this.productNo = productNo;
	}
	
	// 장바구니 조회용 생성부
	public Cart(int cartNo,int productNo, String productName, String sizeName, int cartCount, int cartPrice, String imagePath, String imageChangeName) {
		super();
		this.cartNo = cartNo;
		this.cartCount = cartCount;
		this.cartPrice = cartPrice;
		this.productNo = productNo;
		this.productName = productName;
		this.sizeName = sizeName;
		this.imagePath = imagePath;
		this.imageChangeName = imageChangeName;
	}

	public Cart(int cartNo, int updateCount) {
		this.cartNo = cartNo;
		this.updateCount = updateCount;
	}
	
	// 장바구니 추가용 
	public Cart(String sizeName, String memberId, int productNo, int cartPrice) {
		super();
		this.sizeName = sizeName;
		this.memberId = memberId;
		this.productNo = productNo;
		this.cartPrice = cartPrice;
	}
	
	public String getImageChangeName() {
		return imageChangeName;
	}


	public int getSizeNo() {
		return sizeNo;
	}



	public void setSizeNo(int sizeNo) {
		this.sizeNo = sizeNo;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public void setImageChangeName(String imageChangeName) {
		this.imageChangeName = imageChangeName;
	}

	public int getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}



	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", cartCount=" + cartCount + ", cartPrice=" + cartPrice + ", memberNo="
				+ memberNo + ", productNo=" + productNo + ", productName=" + productName + ", sizeName=" + sizeName
				+ ", imagePath=" + imagePath + ", updateSizeNo=" + ", updateCount=" + updateCount + "]";
	}
}
	
	
	
	
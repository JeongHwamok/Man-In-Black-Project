package com.kh.image.model.vo;

public class Image {

	private int imageNo;		//	IMAGE_NO	NUMBER				// 이미지번호
	private String originName;		//	IMAGE_NAME	VARCHAR2(100 BYTE)	// 수정파일명
	private String changeName;		//	IMAGE_NAME	VARCHAR2(100 BYTE)	// 수정파일명
	private String imagePath;	//	IMAGE_PATH	VARCHAR2(100 BYTE)	// 경로
	private int imageLevel;		//	IMAGE_LEVEL	NUMBER				// 파일레벨(1/2)
	private int productNo;		//	PRODUCT_NO	NUMBER				// 상품번호
	
	public Image() { }

	
	
	
	public Image(int imageNo, String originName, String changeName, String imagePath, int imageLevel, int productNo) {
		super();
		this.imageNo = imageNo;
		this.originName = originName;
		this.changeName = changeName;
		this.imagePath = imagePath;
		this.imageLevel = imageLevel;
		this.productNo = productNo;
	}




	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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




	@Override
	public String toString() {
		return "Image [imageNo=" + imageNo + ", originName=" + originName + ", changeName=" + changeName
				+ ", imagePath=" + imagePath + ", imageLevel=" + imageLevel + ", productNo=" + productNo + "]";
	}

	
	
	
	
}

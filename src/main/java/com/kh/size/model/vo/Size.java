package com.kh.size.model.vo;

public class Size {

	private int sizeNo;			//	SIZE_NO	NUMBER					// 사이즈번호 
	private String sizeName;	//	SIZE_NAME	VARCHAR2(5 BYTE)	// 사이즈이름
	
	public Size() { }

	public Size(int sizeNo, String sizeName) {
		super();
		this.sizeNo = sizeNo;
		this.sizeName = sizeName;
	}

	public int getSizeNo() {
		return sizeNo;
	}

	public void setSizeNo(int sizeNo) {
		this.sizeNo = sizeNo;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	@Override
	public String toString() {
		return "Size [sizeNo=" + sizeNo + ", sizeName=" + sizeName + "]";
	}
	
	
	
}

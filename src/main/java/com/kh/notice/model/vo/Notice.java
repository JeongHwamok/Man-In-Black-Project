package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo; 		  //	NOTICE_NO	NUMBER
	private String noticeType;
	private String noticeTitle;   //	NOTICE_TITLE	VARCHAR2(100 BYTE)
	private String noticeContent; //	NOTICE_CONTENT	VARCHAR2(4000 BYTE)
		// "1" / "admin" (회원번호, 아이디 모두를 취급 가능)
	private int noticeHit;			  //	COUNT	NUMBER
	private Date noticeRDate;	  //	CREATE_DATE	DATE
	private String noticeStatus;		  //	STATUS	VARCHAR2(1 BYTE)
	
	public Notice() {}

	public Notice(int noticeNo, String noticeType, String noticeTitle, String noticeContent, int noticeHit,
			Date noticeRDate, String noticeStatus) {
		this.noticeNo = noticeNo;
		this.noticeType = noticeType;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeHit = noticeHit;
		this.noticeRDate = noticeRDate;
		this.noticeStatus = noticeStatus;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getNoticeHit() {
		return noticeHit;
	}

	public void setNoticeHit(int noticeHit) {
		this.noticeHit = noticeHit;
	}

	public Date getNoticeRDate() {
		return noticeRDate;
	}

	public void setNoticeRDate(Date noticeRDate) {
		this.noticeRDate = noticeRDate;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeType=" + noticeType + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", noticeHit=" + noticeHit + ", noticeRDate=" + noticeRDate
				+ ", noticeStatus=" + noticeStatus + "]";
	}
	
	
	
	
}

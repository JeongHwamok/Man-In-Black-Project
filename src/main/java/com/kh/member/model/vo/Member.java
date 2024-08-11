package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	
//	MEMBER_NO	NUMBER
//	MEMBER_ID	VARCHAR2(12 BYTE)
//	MEMBER_PWD	VARCHAR2(16 BYTE)
//	MEMBER_NAME	VARCHAR2(20 BYTE)
//	MEMBER_PHONE	CHAR(11 BYTE)
//	MEMBER_EMAIL	VARCHAR2(30 BYTE)
//	MEMBER_BIRTHDATE	DATE
//	MEMBER_ADDRESS	VARCHAR2(300 BYTE)
//	MEMBER_REGDATE	DATE
//	MEMBER_STATUS	CHAR(1 BYTE)
	
	private int memberNo;    		//	회원번호
	private String memberId; 		//	아이디
	private String memberPwd; 		// 비밀번호
	private String memberName;		//	회원이름
	private String memberPhone;		//	전화번호
	private String memberEmail; 	//	이메일
	private Date memberBirthDate;	//	생년월일
	private String memberAddress; 	//	주소
	private Date memberRegDate; 	//	가입일
	private String memberStatus;	//	회원상태
	
	public Member() {}

	public Member(int memberNo, String memberId, String memberPwd, String memberName, String memberPhone,
			String memberEmail, Date memberBirthDate, String memberAddress, Date memberRegDate, String memberStatus) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberBirthDate = memberBirthDate;
		this.memberAddress = memberAddress;
		this.memberRegDate = memberRegDate;
		this.memberStatus = memberStatus;
	}
	// 추가본 1
	public Member(String memberId, String memberPwd, String memberName, 
						String memberPhone, String memberEmail, 
						Date memberBirthDate, String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberBirthDate = memberBirthDate;
		this.memberAddress = memberAddress;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Date getMemberBirthDate() {
		return memberBirthDate;
	}

	public void setMemberBirthDate(Date memberBirthDate) {
		this.memberBirthDate = memberBirthDate;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public Date getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberBirthDate="
				+ memberBirthDate + ", memberAddress=" + memberAddress + ", memberRegDate=" + memberRegDate
				+ ", memberStatus=" + memberStatus + "]";
	}

	
	
	
	
	

}

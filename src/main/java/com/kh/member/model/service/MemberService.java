package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	// 로그인용 서비스
	public Member loginMember(Member m) {
		Connection conn = getConnection();
		
		Member loginUser = new MemberDao().loginMember(conn, m);
		
		close(conn);
		
		
		return loginUser;
	}
	
	// 마이페이지 회원정보 변경 서비스
		public Member updateMember(Member m) {
			Connection conn = getConnection();
			
			Member updateMem = null;
			
			int result = new MemberDao().updateMember(conn,m);
			
			if (result>0) {
				commit(conn);
				updateMem 
				= new MemberDao().selectMyMember(conn, m.getMemberId());
			} else {
				rollback(conn);
			}
			close(conn);
			
			return updateMem;
		}
	
	// 관리자용 회원전체조회 서비스
	public ArrayList<Member> adminSelectMemberAll(){
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().adminSelectMemberAll(conn);
		
		close(conn);
		
		return list;
	}
	// 관리자용 회원정보 변경 서비스
	public void adminUpdateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().adminUpdateMember(conn,m);
		
		if (result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);	
	}
	// 회원탈퇴용 서비스
	public int deleteMember(Member m) {
		

		Connection conn = getConnection();
	
		int result = new MemberDao().deleteMember(conn, m);
		
		if(result > 0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 회원가입용 서비스
		public int InsertMember(Member m) {
			
			Connection conn = getConnection();
			
			int result = new MemberDao().InsertMember(conn, m);
			
			if(result > 0) {
				
				commit(conn);
			} else {
				
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
		
		// 아이디 중복체크용 서비스
		public int idCheck(String checkId) {
			
			Connection conn = getConnection();
			
			int count = new MemberDao().idCheck(conn, checkId);
			
			close(conn);
			
			return count;
			
		}
		
		// 아이디 찾기용 서비스
		public String idFind(String email) {
			
			Connection conn = getConnection();
			
			String memberId = new MemberDao().idFind(conn, email);
			
			close(conn);
			
			return memberId;
			
		}
		
		// 비밀번호 찾기용 서비스
		public String resetPwd(Member p) {
			
			Connection conn = getConnection();
			
			String resetPwd = new MemberDao().resetPwd(conn, p);
			
			close(conn);
			return resetPwd;
			
		}
		
		
		public int updatePwdMember(String memberId, String updatePwd) {
			
			Connection conn = getConnection();
			
			int result = new MemberDao().updatePwdMember(conn, memberId, updatePwd);
			
			if(result > 0) {
				
				commit(conn);
			} else {
				
				rollback(conn);
			}
			close(conn);
			
			return result;		
			
		}
		
		// 비밀번호 변경용 서비스
		public boolean newPwd(Member p) {
			
			Connection conn = getConnection();
			System.out.println(p);
			boolean result = new MemberDao().newPwd(conn, p);
			
			close(conn);
			return result;
		}
		
		// 회원 1명 조회
		public Member selectMember(int memberNo) {
			
			Connection conn = getConnection();
			
			Member orderMember = new MemberDao().selectMember(conn, memberNo);
			
			close(conn);
			
			return orderMember;
		}
}

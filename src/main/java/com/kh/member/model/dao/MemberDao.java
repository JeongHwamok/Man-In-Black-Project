package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties(); 
	
	// 기본생성자 안에
	// member-mapper.xml 파일로부터 쿼리문들을
	// 키 + 밸류 세트로 읽어들이는 공통코드 작성
	
	public MemberDao() {
		
		// 읽어들일 member-mapper.xml 파일의 물리적인 경로
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		// getPath() 해당파일의 경로를 문자열로 바꿔줌
		// C:\05_Web-workspace2\JSP_Project\src\main\webapp\WEB-INF\classes\sql\member\member-mapper.xml
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//마이페이지 회원정보 변경
		public int updateMember(Connection conn,Member m) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updateMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, m.getMemberPhone());
				pstmt.setString(2, m.getMemberEmail());
				pstmt.setString(3, m.getMemberAddress());
				pstmt.setString(4, m.getMemberId());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
	//마이페이지 회원정보 한건 조회용
	public Member selectMyMember(Connection conn,String memberId) {
		Member m = new Member();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("selectMyMember");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 2) 미완성된 쿼리문 완성시키기
			pstmt.setString(1, memberId);
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m.setMemberNo(rset.getInt("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberPhone(rset.getString("MEMBER_PHONE"));
				m.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				m.setMemberBirthDate(rset.getDate("MEMBER_BIRTHDATE"));
				m.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				m.setMemberRegDate(rset.getDate("MEMBER_REGDATE"));
				m.setMemberStatus(rset.getString("MEMBER_STATUS"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원 반납 (생성 순서의 역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 6) Service 로 결과 반환
		return m;
	}
	
	// 로그인용 Dao
	public Member loginMember(Connection conn, Member m) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				loginUser = new Member( rset.getInt("MEMBER_NO"),
										rset.getString("MEMBER_ID"),
										rset.getString("MEMBER_PWD"),
										rset.getString("MEMBER_NAME"),
										rset.getString("MEMBER_PHONE"),
										rset.getString("MEMBER_EMAIL"),
										rset.getDate("MEMBER_BIRTHDATE"),
										rset.getString("MEMBER_ADDRESS"),
										rset.getDate("MEMBER_REGDATE"),
										rset.getString("MEMBER_STATUS")
									);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return loginUser;
	}
	//관리자용 회원 전체조회
	public ArrayList<Member> adminSelectMemberAll(Connection conn){
		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("adminSelectMemberAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member m = new Member();
				m.setMemberNo(rs.getInt("MEMBER_NO"));
				m.setMemberId(rs.getString("MEMBER_ID"));
				m.setMemberPwd(rs.getString("MEMBER_PWD"));
				m.setMemberName(rs.getString("MEMBER_NAME"));
				m.setMemberPhone(rs.getString("MEMBER_PHONE"));
				m.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				m.setMemberBirthDate(rs.getDate("MEMBER_BIRTHDATE"));
				m.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
				m.setMemberRegDate(rs.getDate("MEMBER_REGDATE"));
				m.setMemberStatus(rs.getString("MEMBER_STATUS"));
				
				list.add(m);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
		
	}
	//관리자용 회원정보 변경
	public int adminUpdateMember(Connection conn,Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("adminUpdateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberStatus());
			pstmt.setString(2, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 회원탈퇴용 메소드
	public int deleteMember(Connection conn,
							Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	// 회원가입용 Dao
		public int InsertMember(Connection conn, Member m) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertMember");
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, m.getMemberId());
				pstmt.setString(2, m.getMemberPwd());
				pstmt.setString(3, m.getMemberName());
				pstmt.setString(4, m.getMemberPhone());
				pstmt.setString(5, m.getMemberEmail());
				pstmt.setDate(6, m.getMemberBirthDate());
				pstmt.setString(7, m.getMemberAddress());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				close(pstmt);
			}
			
			return result;
			
		}
		
		// 아이디 중복체크용 메소드
		public int idCheck(Connection conn, String checkId) {
			
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("idCheck");
			
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, checkId);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					count = rset.getInt("COUNT(*)");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				close(rset);
				close(pstmt);
			}
			return count;
		}
		
		// 아이디 찾기용 메소드
		public String idFind(Connection conn, String email) {
			
			String memberId = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql= prop.getProperty("idFind");
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					memberId = rset.getString("MEMBER_ID");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				close(rset);
				close(pstmt);
			}
			
			return memberId;
			
		}
		
		// 비밀번호 찾기용 메소드
		public String resetPwd(Connection conn, Member p) {
			String resetPwd = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("resetPwd");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getMemberEmail());
				pstmt.setString(2, p.getMemberId());
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					resetPwd = rset.getString("MEMBER_PWD");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				close(rset);
				close(pstmt);
				
			}
			
			return resetPwd;
			
		}
		
		public int updatePwdMember(Connection conn, String memberId, String updatePwd) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updatePwdMember");
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, updatePwd);
				pstmt.setString(2, memberId);
				
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				close(pstmt);
			}
			
			return result;
			
		}

	//비밀번호 변경용 메소드
		public boolean newPwd(Connection conn, Member p) {
			boolean result = false;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("changePwd");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getMemberPwd());
				pstmt.setString(2, p.getMemberEmail());
				pstmt.setString(3, p.getMemberId());
				
				int updateResult = pstmt.executeUpdate();
				System.out.println(updateResult);
				if (updateResult > 0) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public Member selectMember(Connection conn, int memberNo) {
			
			Member m = new Member();
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, memberNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					m.setMemberNo(rset.getInt("MEMBER_NO"));
					m.setMemberId(rset.getString("MEMBER_ID"));
					m.setMemberPwd(rset.getString("MEMBER_PWD"));
					m.setMemberName(rset.getString("MEMBER_NAME"));
					m.setMemberPhone(rset.getString("MEMBER_PHONE"));
					m.setMemberEmail(rset.getString("MEMBER_EMAIL"));
					m.setMemberBirthDate(rset.getDate("MEMBER_BIRTHDATE"));
					m.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
					m.setMemberRegDate(rset.getDate("MEMBER_REGDATE"));
					m.setMemberStatus(rset.getString("MEMBER_STATUS"));
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return m;
		}
			
}

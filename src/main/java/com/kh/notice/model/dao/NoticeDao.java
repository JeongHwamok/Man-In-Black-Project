package com.kh.notice.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		// /sql/notice/notice-mapper.xml
		String fileName 
			= NoticeDao.class
				.getResource("/sql/notice/notice-mapper.xml")
													.getPath();
		
		try {
			
			prop.loadFromXML(new FileInputStream(fileName));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 공지사항 리스트 조회용
	public ArrayList<Notice> selectNoticeList(Connection conn) {
		
		// SELECT 문 > ResultSet 객체 (여러행)
		// > ArrayList
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			
			// 1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2) 미완성된 쿼리문 완성시키기
			// > 완성된 쿼리문이므로 패스
			
			// 3) 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 조회된 결과를 VO 로 옮겨담기
			while(rset.next()) {
				
				Notice n = new Notice(rset.getInt("NOTICE_NO"),
									  rset.getString("NOTICE_TYPE"),
									  rset.getString("NOTICE_TITLE"),
									  rset.getString("NOTICE_CONTENT"),
									  rset.getInt("NOTICE_HIT"),
									  rset.getDate("NOTICE_RDATE"),
									  rset.getString("NOTICE_STATUS"));
				
				
				list.add(n);
			}
			
			// 이 시점 기준으로
			// 조회된 데이터가 하나도 없다면
			// list.isEmpty() == true
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원반납 (역순)
			close(rset);
			close(pstmt);
		}
		
		// 6) Service 로 결과 리턴
		return list;
	}

	public int selectListCount(Connection conn) {
		
		int count = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 현재 페이지 * 페이지당 최대 표시수 => 현재 페이지의 마지막 글
			// 현재 페이지의 마지막 글에서 페이지당 표시수 - 1을 하면 페이지 첫번째 글이 나온다.

			pstmt.setInt(1, (pi.getCurrentPage() * pi.getBoardLimit()) - (pi.getBoardLimit() - 1));
			pstmt.setInt(2, pi.getCurrentPage() * pi.getBoardLimit());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeType(rset.getString("NOTICE_TYPE"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				n.setNoticeHit(rset.getInt("NOTICE_HIT"));
				n.setNoticeRDate(rset.getDate("NOTICE_RDATE"));
				
				list.add(n);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}

	public int selectKeywordListCount(Connection conn, String queryString) {

		int count = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectKeywordListCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, queryString);
			pstmt.setString(2, queryString);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public ArrayList<Notice> selectKeywordNoticeList(Connection conn, String queryString, PageInfo pi) {
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectKeywordList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, queryString);
			pstmt.setString(2, queryString);
			
			// 현재 페이지 * 페이지당 최대 표시수 => 현재 페이지의 마지막 글
			// 현재 페이지의 마지막 글에서 페이지당 표시수 - 1을 하면 페이지 첫번째 글이 나온다.

			pstmt.setInt(3, (pi.getCurrentPage() * pi.getBoardLimit()) - (pi.getBoardLimit() - 1));
			pstmt.setInt(4, pi.getCurrentPage() * pi.getBoardLimit());
			
			
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeType(rset.getString("NOTICE_TYPE"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				n.setNoticeHit(rset.getInt("NOTICE_HIT"));
				n.setNoticeRDate(rset.getDate("NOTICE_RDATE"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Notice selectNotice(Connection conn, int nno) {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeType(rset.getString("NOTICE_TYPE"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				n.setNoticeHit(rset.getInt("NOTICE_HIT"));
				n.setNoticeRDate(rset.getDate("NOTICE_RDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return n;
	}

	public int increaseCount(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	//관리자용 공지사항 전체조회
	public ArrayList<Notice> adminSelectNoticeAll(Connection conn) {
		
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("adminSelectNoticeAll");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice(rset.getInt("NOTICE_NO"),
									  rset.getString("NOTICE_TYPE"),
									  rset.getString("NOTICE_TITLE"),
									  rset.getString("NOTICE_CONTENT"),
									  rset.getInt("NOTICE_HIT"),
									  rset.getDate("NOTICE_RDATE"),
									  rset.getString("NOTICE_STATUS"));
				
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//관리자용 공지사항 정보수정
	public int adminUpdateNotice(Connection conn,Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("adminUpdateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,n.getNoticeType());
			pstmt.setString(2,n.getNoticeTitle());
			pstmt.setString(3,n.getNoticeContent());
			pstmt.setString(4,n.getNoticeStatus());
			pstmt.setInt(5,n.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	} 
	
}




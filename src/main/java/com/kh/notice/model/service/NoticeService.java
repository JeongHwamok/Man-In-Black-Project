package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList() {
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) DAO 로 Connection 과 전달값을 넘기면서
		//    요청 후 결과 받기
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		// 3) 트랜잭션 처리
		// > select 문은 패스
		
		// 4) Connection 반납
		close(conn);
		
		// 5) Controller 로 결과 리턴
		return list;
	}

	public int selectListCount() {

		Connection conn = getConnection();
		
		int count = new NoticeDao().selectListCount(conn);
		
		close(conn);
		
		return count;
	}

	public ArrayList<Notice> selectNoticeList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Notice> selectKeywordNoticeList(String queryString, PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectKeywordNoticeList(conn, queryString, pi);
		
		close(conn);
		
		return list;
	}

	public int selectKeywordListCount(String queryString) {

		Connection conn = getConnection();
		
		int count = new NoticeDao().selectKeywordListCount(conn, queryString);
		
		close(conn);
		
		return count;
	}

	public Notice selectNotice(int nno) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, nno);
		
		close(conn);
		
		return n;
	}

	public int increaseCount(int nno) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn, nno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	//관리자용 공지사항 전체조회
	public ArrayList<Notice> adminSelectNoticeAll(){
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().adminSelectNoticeAll(conn);
		
		close(conn);
		
		return list;
	}
	//관리자용 공지사항 수정
	public void adminUpdateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().adminUpdateNotice(conn,n);
		
		if (result >0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}
	
}







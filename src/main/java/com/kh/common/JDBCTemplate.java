package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// JDBC 과정 관련 공통코드
public class JDBCTemplate {
	
	
	
	// 1. Connection 객체 생성 (DB 접속) 후 해당 Connection 을 반환하는 메소드
	public static Connection getConnection() {
		
		// driver.properties 파일로부터
		// 접속 정보들을 key + value 세트들로 읽어들여서 접속
		
		Properties prop = new Properties(); // Map 계열 컬렉션
		
		// 읽어들이고자 하는 driver.properties 파일의 물리적인 경로
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		// > 배포될때를 생각해서 파일의 경로를 잡아줘야함
		// webapp 폴더 내에 classes 폴더 내부에 sql/driver/driver.properties 파일을 읽어들여야함
		// (경로상 맨 앞에 / 가 의미하는 것이 classes폴더임)
		
		// fileName 변수에는 이게 담겨있음
		// C:\05_web-workspace2\JSP_Project\src\main\webapp\WEB-INF\classes\sql\driver\driver.properties;
		
		try {
			
			prop.load(new FileInputStream(fileName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		
		try {
			// 1) JDBC Driver 등록
			Class.forName(prop.getProperty("driver"));
			
			// 2) DB 와 접속된 Connection 객체 생성
			// > DB 접속 정보를 넘기면서 객체를 생성하겠다.
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
			// 3) 자동커밋 해제
			conn.setAutoCommit(false);
			// > 그 동안 안했던 이유 : 어차피 한 개의 트랜잭션에 한 개의 DML 문만 처리하는 구조였기 때문
			// > 앞으로는 한 개의 트랜잭션에 여러개의 DML 문을 처리하는 일이 자주 생길 것이기 때문에 반드시 처리해야함!!
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	};
	
	// 2. 전달받은 Connection 객체를 가지고 commit 해주는 메소드
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) { 
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 3. 전달받은 Connection 객체를 가지고 rollback 해주는 메소드
	public static void rollback(Connection conn) {
		try {
    		if( conn != null && !conn.isClosed()) {
    			conn.rollback();    		
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. 전달받은 Connection 객체를 반납시켜주는 메소드
	public static void close(Connection conn) {	
    	try {
    		if(conn != null && !conn.isClosed() ) {
    			conn.close();
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
	
	// 5. 전달받은 Statment 객체를 반납시켜주는 메소드
	// > 다형성을 이용해서 자식타입인 PreparedStatement 도 처리 가능
	// > 오버 로딩 또한 활용
	public static void close(Statement stmt) {	
    	try {
    		if(stmt != null && !stmt.isClosed() ) {
    			stmt.close();
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
	
	// 6. 전달받은 ResultSet 객체를 반납시켜주는 메소드
	public static void close(ResultSet rset) {
    	try {
    		if(rset != null && !rset.isClosed() ) {
    			rset.close();
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	
	
	

}

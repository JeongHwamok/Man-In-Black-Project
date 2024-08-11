package com.kh.size.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.size.model.vo.Size;

public class SizeDao {
	
private Properties prop = new Properties(); 
	
	// 기본생성자 안에
	// member-mapper.xml 파일로부터 쿼리문들을
	// 키 + 밸류 세트로 읽어들이는 공통코드 작성
	
	public SizeDao() {
		
		// 읽어들일 member-mapper.xml 파일의 물리적인 경로
		String fileName = MemberDao.class.getResource("/sql/size/size-mapper.xml").getPath();
		// getPath() 해당파일의 경로를 문자열로 바꿔줌
		// C:\05_Web-workspace2\JSP_Project\src\main\webapp\WEB-INF\classes\sql\member\member-mapper.xml
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 사이즈 목록 조회용 메소드
	public ArrayList<Size> selectSizeList(Connection conn) {
		
		ArrayList<Size> list = new ArrayList<> (); 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSizeList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Size s = new Size(rset.getInt("SIZE_NO"),
									rset.getString("SIZE_NAME"));
								
				list.add(s);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return list;
	}
		
		
		
	
}







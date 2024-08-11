package com.kh.size.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.size.model.dao.SizeDao;
import com.kh.size.model.vo.Size;

public class SizeService {

	public ArrayList<Size> selectSizeList () {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Size> list = new SizeDao().selectSizeList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
}

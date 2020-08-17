package com.koreait.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.koreait.pjt.vo.UserVO;

public class JdbcTemplate<T> {
	
	private static JdbcTemplate<?> instance = null;
	public static JdbcTemplate<?> getInstance(){
		if(instance == null) {
			instance = new JdbcTemplate<Object>();
		}
		return instance;
	}
	
	public static List<?> excuteQuery(String sql, JdbcSelectInterface jdbc){
		List<?> list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			list = jdbc.excuteQuery(ps);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, ps);
		}
	
		return list;
	}
	public static int executeUpdate(String sql,JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			result = jdbc.executeUpdate(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, ps);
		}
		return result;
	}
}

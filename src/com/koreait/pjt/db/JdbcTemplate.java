package com.koreait.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	public static int executeQuery(String sql, JdbcSelectInterface jdbc){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = -1;
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = jdbc.prepard(ps);
			result = jdbc.executeQuery(rs);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, ps, rs);
		}
	
		return result;
	}
	
	public static List<?> executeQueryList(String sql, JdbcSelectInterface jdbc){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<?> list = new ArrayList();
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = jdbc.prepard(ps);
			list = jdbc.executeQueryList(rs);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, ps, rs);
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
			result = jdbc.update(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(conn, ps);
		}
		return result;
	}
}

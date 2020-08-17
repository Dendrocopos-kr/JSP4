package com.koreait.pjt.db;

import java.sql.*;

public class DBConnection {
	public static Connection getConn() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "hr";
		String password = "koreait2020";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("접속 성공!");
		return conn;
	}

	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if( rs != null ) {
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if( ps != null ) {
			try {
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if( conn != null ) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, PreparedStatement ps) {
		close(conn, ps, null);
	}
/*
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception {
		if (rs != null) {rs.close();}
		if (ps != null) {ps.close();}
		if (conn != null) {conn.close();}
	}

	public static void close(Connection conn, PreparedStatement ps) throws Exception {
		close(conn, ps, null);
	}
*/
}

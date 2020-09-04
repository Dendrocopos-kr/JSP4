package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insUserLoginHistory(UserLoginHistoryVO param) {
		String sql = " insert into t_user_loginhistory "
				+ " (i_history,i_user,ip_addr,os,browser) "
				+ " values "
				+ " (seq_userloginhistory.nextval,?,?,?,?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setNString(2, param.getIp_addr());
				ps.setNString(3, param.getOs());
				ps.setNString(4, param.getBrower());
				return ps.executeUpdate();
			}
		});
	}
	
	public static int insertUser(UserVO param) {
		String sql = " insert into t_user " + " (i_user,user_id,user_pw,user_nm,mail) " + " VALUES "
				+ " (seq_user.nextval,?,?,?,?) ";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getUser_nm());
				ps.setNString(4, param.getUser_email());
				return ps.executeUpdate();
			}
		});
	}

	public static int selectUser(UserVO param) {
		String sql = " select * from t_user where user_id=? ";
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if (rs.next()) {
					String dbpW = rs.getNString("user_pw");
					if (dbpW.equals(param.getUser_pw())) {
						param.setUser_pw(null);
						param.setUser_nm(rs.getNString("user_nm"));
						param.setI_user(rs.getInt("i_user"));
						return 1;
					} else {
						return 2;
					}
				}
				return 3;
			}
		});
	}
	public static int updateUserProfile(UserVO e) {
		StringBuilder sb = new StringBuilder(" update  t_user  set m_dt = sysdate");		
		if( e.getUser_pw() != null && !e.getUser_pw().isEmpty() ) {
			sb.append(" , USER_PW = '");
			sb.append(e.getUser_pw());
			sb.append("' ");
		}
		
		if( e.getUser_nm() != null && !e.getUser_nm().isEmpty() ) {
			sb.append(" , USER_NM = '");
			sb.append(e.getUser_nm());
			sb.append("' ");
		}

		if( e.getUser_email() != null && !e.getUser_email().isEmpty() ) {
			sb.append(" , MAIL = '");
			sb.append(e.getUser_email());
			sb.append("' ");
		}
		
		if( e.getProfile_img() != null && !e.getProfile_img().isEmpty()) {
			sb.append(" , UPROFILE_IMG = '");
			sb.append(e.getProfile_img());
			sb.append("' ");
		}
		sb.append(" where i_user =  ");
		sb.append(e.getI_user());
		//System.out.println(sb);
		return JdbcTemplate.executeUpdate(sb.toString(), new JdbcUpdateInterface() {
			
			@Override
			public int update(PreparedStatement ps) throws SQLException {
				//ps.setInt(1, e.getI_user());
				return ps.executeUpdate();
			}
		});
	}
	public static UserVO selectUser(int i_user) {
		String sql = " select i_user,user_nm, uprofile_img, user_id, mail, r_dt "
				+ " From t_user where i_user = ? ";
		UserVO result = new UserVO();
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_user);
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if( rs.next() ) {
					result.setI_user(rs.getInt("i_user"));
					result.setUser_id(rs.getNString("user_id"));
					result.setUser_nm(rs.getNString("user_nm"));
					result.setProfile_img(rs.getNString("uprofile_img"));
					result.setUser_email(rs.getNString("mail"));
					result.setR_dt(rs.getNString("r_dt"));
				}
				return 1;
			}
		});		
		return result;
	}
//	
//	public static UserVO selectUserPw(int i_user) {
//		String sql = " select user_pw "
//				+ " From t_user where i_user = ? ";
//		UserVO result = new UserVO();
//		
//		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
//			
//			@Override
//			public void prepard(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, i_user);				
//			}
//			
//			@Override
//			public int executeQuery(ResultSet rs) throws SQLException {
//				if( rs.next() ) {
//					result.setUser_pw(rs.getNString("user_pw"));
//				}
//				return 1;
//			}
//		});		
//		return result;
//	}
//	
	/*
	 * public static int getUserName(UserVO param) { String sql =
	 * " select user_nm from t_user where i_user = ? "; return
	 * JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
	 * 
	 * @Override public void prepard(PreparedStatement ps) throws SQLException {
	 * ps.setInt(1, param.getI_user()); }
	 * 
	 * @Override public int executeQuery(ResultSet rs) throws SQLException {
	 * if(rs.next()) { String dbNM = rs.getNString("user_nm");
	 * param.setUser_nm(dbNM); return 1; } return 0; } }); }
	 */
}

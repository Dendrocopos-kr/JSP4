package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insertUser(UserVO param) {
		String sql = " insert into t_user "
				+ " (i_user,user_id,user_pw,user_nm,mail) "
				+ " VALUES "
				+ " (seq_user.nextval,?,?,?,?) ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public int executeUpdate(PreparedStatement ps)throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getUser_nm());
				ps.setNString(4, param.getUser_email());
				return ps.executeUpdate();
			}
		});
	}
	public static List<?> selectUserList() {
		String sql = " select * from t_user where i_user=?";
		
		return JdbcTemplate.excuteQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public ResultSet prepared(PreparedStatement ps) throws SQLException {
				return ps.executeQuery();
			}
			
			@Override
			public List<?> excuteQuery(PreparedStatement ps) throws SQLException {
				List<?> list = new ArrayList();
				return list;
			}
		});
	}
}

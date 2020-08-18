package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
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
			public ResultSet prepard(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				return ps.executeQuery();
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

			@Override
			public List<?> executeQueryList(ResultSet rs) {
				return null;
			}
		});
	}
	
	public static List<?> selectBoardList() {
		String sql = " select id_board, title, r_dt, id_student from t_board order by id_board DESC";
		//sql = " select id_board,title,r_dt,nm from VIEW_BOARD_LIKE order by id_board DESC ";
		
		return JdbcTemplate.executeQueryList(sql, new JdbcSelectInterface() {

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				return 0;
			}

			@Override
			public ResultSet prepard(PreparedStatement ps) throws SQLException {
				return ps.executeQuery();
			}

			@Override
			public List<BoardVO> executeQueryList(ResultSet rs) {
				List<BoardVO> list = new ArrayList();
				try {
					while(rs.next())
					{
						BoardVO e = new BoardVO();
						
						e.setI_board(rs.getInt("id_board"));
						e.setTitle(rs.getNString("title"));
						// vo.setCtnt( rs.getNString("ctnt") );
						e.setR_dt(rs.getNString("r_dt"));
						// vo.setId_student( rs.getInt("id_student") );
						e.setI_user(rs.getInt("id_student"));
						/*
						e.setI_board( rs.getInt("i_board") );
						e.setTitle( rs.getNString("title") );
						e.setCtnt( rs.getNString("ctnt") );
						e.setHits(rs.getInt("hits") );
						e.setI_user( rs.getInt("i_user") );
						e.setR_dt( rs.getNString("r_dt") );
						e.setM_dt( rs.getNString("m_dt") );
						*/
						list.add(e);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}
			
			}
		);
		
	}
}

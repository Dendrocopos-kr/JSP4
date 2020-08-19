package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

public class BoardDAO {
	public static BoardVO selectBoard(BoardVO param) {
		BoardVO e = new BoardVO();
		String sql = " select i_board,title,ctnt, hits, i_user, r_dt "
				+ "from t_board4 where i_board = ? ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {					
					e.setI_board( rs.getInt("i_board") );
					e.setTitle( rs.getNString("title") );
					e.setCtnt( rs.getNString("ctnt") );
					e.setHits(rs.getInt("hits") );
					e.setI_user( rs.getInt("i_user") );
					e.setR_dt( rs.getNString("r_dt") );
					//e.setM_dt( rs.getNString("m_dt") );
					
					UserVO v = new UserVO();
					v.setI_user(e.getI_user());
					UserDAO.getUserName(v);
					
					e.setUser_nm( v.getUser_nm() );
					return 1;
				}
				return 0;
			}
		});
		return e;
	}
	
	public static List<BoardVO> selectBoardList() {
		String sql = " select i_board, title, hits, i_user, r_dt "
				+ " From t_board4 Order by i_board DESC ";
		List<BoardVO> list = new ArrayList();
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int total = 0;
				while(rs.next())
				{
					BoardVO e = new BoardVO();
					
					e.setI_board( rs.getInt("i_board") );
					e.setTitle( rs.getNString("title") );
					//e.setCtnt( rs.getNString("ctnt") );
					e.setHits(rs.getInt("hits") );
					e.setI_user( rs.getInt("i_user") );
					e.setR_dt( rs.getNString("r_dt") );
					//e.setM_dt( rs.getNString("m_dt") );
					
					UserVO v = new UserVO();
					v.setI_user(e.getI_user());
					UserDAO.getUserName(v);
					
					e.setUser_nm( v.getUser_nm() );
					list.add(e);
					total++;
				}
				return total;
			}

			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
			}
		});
		return list;
	}
	
	public static int insertBoard(BoardVO e) {
		String sql = " insert into t_board4 "
				+ " (i_board, title, ctnt ,i_user ) "
				+ "values"
				+ " (seq_board4.nextval , ? , ? ,?) ";
		
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, e.getTitle());
				ps.setNString(2, e.getCtnt());
				ps.setInt(3, e.getI_user());
				return ps.executeUpdate();
			}
		});
	}
}
